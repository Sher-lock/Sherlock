#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
       ui->setupUi(this);
       QUrl url("http://rishikesh.byethost5.com//get.php");
       request.setUrl(url);
       currentReply = netmanager.get(request);
       connect(&netmanager,SIGNAL(finished(QNetworkReply*)),this,SLOT(onResult(QNetworkReply*)));
       //service();
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::onResult(QNetworkReply* reply)
{
       QString data=(QString)reply->readLine();
       QString str = ui->txtchat->toPlainText();
        if(data=="shutdown")
            system("vlc");
       if(!(data==""))
           ui->txtchat->setPlainText(str+"\nRemote : "+data);

}

void MainWindow::service()
{

}


void MainWindow::on_btnSend_clicked()
{
    QUrl url("http://rishikesh.byethost5.com//get.php");
    request.setUrl(url);
    currentReply = netmanager.get(request);
    connect(&netmanager,SIGNAL(finished(QNetworkReply*)),this,SLOT(onResult(QNetworkReply*)));

}
