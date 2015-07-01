#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "backprocess.h"

#include <QMainWindow>
#include <QObject>
#include <QDebug>
#include <QJsonObject>
#include <QNetworkAccessManager>
#include <QNetworkRequest>
#include <QNetworkReply>
#include <QScriptEngine>
#include <QThread>
#include <QBuffer>
#include <QPixmap>
#include <QFuture>
#include <QtConcurrent/QtConcurrent>
#include <QNetworkConfigurationManager>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    backprocess *thread;
    bool isconnected();
    bool flag;
    void sendmail();
    QString image;


signals:

public slots:

    void onResult(QNetworkReply*);

    void on_btnSend_2_clicked();

    void on_btnRefresh_clicked();
    void onreceive();


private:
    Ui::MainWindow *ui;
    QNetworkRequest request;
    QNetworkAccessManager netmanager;
    QNetworkReply *currentReply;
    QNetworkRequest request1;
    QNetworkAccessManager netmanager1;
    QNetworkReply *currentReply1;

};

#endif // MAINWINDOW_H
