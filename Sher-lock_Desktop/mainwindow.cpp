#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "../src/SmtpMime"
#include <QtCore>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
       ui->setupUi(this);
       flag=false;
       QUrl url("http://rishikesh.byethost5.com//get.php");
       QUrl url1("http://rishikesh.byethost5.com//putnotification.php");
       request1.setUrl(url1);
       request.setUrl(url);
       connect(&netmanager,SIGNAL(finished(QNetworkReply*)),this,SLOT(onResult(QNetworkReply*)));
       connect(&netmanager1,SIGNAL(finished(QNetworkReply*)),this,SLOT(onResult(QNetworkReply*)));
       thread=new backprocess(this);
       connect(thread,SIGNAL(receive()),this,SLOT(onreceive()));
       thread->start();
}

MainWindow::~MainWindow()
{
    delete ui;
}

bool MainWindow::isconnected()
{
    QNetworkConfigurationManager mgr;
    QList<QNetworkConfiguration> activeConfigs = mgr.allConfigurations(QNetworkConfiguration::Active);
    if(activeConfigs.count() == 2)
        return true;
    else
        return false;
}


void MainWindow::onreceive()
{
    currentReply = netmanager.get(request);

    //check connection
    if(isconnected()==true)
    {
        if((flag==false))
        {
            currentReply1 = netmanager1.get(request1);
            qDebug()<<"connected";
            flag=true;
        }
    }
    else
    {
        qDebug()<<"not connected";
        flag=false;
    }
}


void MainWindow::sendmail()
{
    SmtpClient smtp("smtp.gmail.com", 465, SmtpClient::SslConnection);
    smtp.setUser("prasad.ghangal@gmail.com");
    smtp.setPassword("SWORDFISH121349");
    MimeMessage message;
    message.setSender(new EmailAddress("prasad.ghangal@gmail.com", "Sher-Lock_Desktop"));
    message.addRecipient(new EmailAddress("prasad.ghangal@gmail.com", "SRP Remote"));
    message.setSubject("Sher-Lock Support");
    MimeText text;
   text.setText("Hi!\n Find the requested attachment");
   message.addPart(&text);

//   QProcess process;
//   process.start("ls -t | cut -c1-39 |head -1");
//   process.waitForFinished(-1); // will wait forever until finished

//   QString stdout = process.readAllStandardOutput();
//   qDebug()<<stdout;
   // Now we create the attachment object
   MimeAttachment attachment (new QFile(image));

   // the file type can be setted. (by default is application/octet-stream)
   attachment.setContentType("image/png");

   // Now add it to message
   message.addPart(&attachment);

   // Now we can send the mail

   if (!smtp.connectToHost()) {
       qDebug() << "Failed to connect to host!" << endl;
       //return -1;
   }


   if (!smtp.login()) {
       qDebug() << "Failed to login!" << endl;
   //    return -2;
   }

   if (!smtp.sendMail(message)) {
       qDebug() << "Failed to send mail!" << endl;
     //  return -3;
   }


   smtp.quit();

}


void MainWindow::onResult(QNetworkReply* reply)
{
       QString data1=(QString)reply->readLine();
       QString str = ui->txtchat->toPlainText();
        if(data1=="shutdown")
        {
            ui->txtchat->setPlainText("\nRemote : "+data1);
            system("sudo -S shutdown -P 0 < /home/prasad/pwd");
        }
        else if(data1=="screenshot")
        {
            system("gnome-screenshot -f \"/home/prasad/capture.png\"");
            image="/home/prasad/capture.png";
            sendmail();
        }
        else if(data1=="capture")
        {
            system("streamer -f jpeg -o /home/prasad/capture.jpeg");
            image="/home/prasad/capture.jpeg";
            sendmail();
        }
        else if((data1!="")&&(data1!="wow we did it"))
        {
            const char *cmd = data1.toLatin1().data();
            system(cmd);
        }
       if(!(data1==""))
            ui->txtchat->setPlainText("\nRemote : "+data1);
 //       qDebug()<<"d="<<data;
}


void MainWindow::on_btnSend_2_clicked()
{
    system("streamer -f jpeg -o /home/prasad/capture.jpeg");
    image="/home/prasad/capture.jpeg";
    sendmail();
}

void MainWindow::on_btnRefresh_clicked()
{
    currentReply = netmanager.get(request);
}
