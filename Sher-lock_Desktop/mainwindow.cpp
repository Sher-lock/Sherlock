#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    socket=new QTcpSocket(this);
    server=new QTcpServer(this);
    connect(server,SIGNAL(newConnection()),this,SLOT(addConnection()));
    if(!server->listen(QHostAddress::Any,1234))
        ui->txtmsg->setText("Could not start");
    else
    {
        ui->txtmsg->setText("Server started");
        //connect(websocketserver,SIGNAL(newConnection()),this,SLOT(newConnection()));
    }

}


MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow :: addConnection()
{
    QBuffer* buffer = new QBuffer(this);
    buffer->open(QIODevice::ReadWrite);

    socket=server->nextPendingConnection();
    connect(socket, SIGNAL(readyRead()),SLOT(processMessage()));
    ui->txtmsg->setText("Done");

}


void MainWindow::processMessage()
{
    QByteArray ba = socket->readLine();
    QString str=ui->txtchat->toPlainText();
    ui->txtchat->setPlainText(str+"\nRemote : "+ba);
}

void MainWindow::on_btnSend_clicked()
{
    QString str=ui->txtmsg->toPlainText();
    //QByteArray ba=str;
    socket->write("Test");
   // ui->txtchat->setPlainText("\nDesktop : "+str);
    ui->txtmsg->setText("");

}
