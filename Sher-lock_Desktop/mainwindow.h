#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QObject>
#include <QDebug>
#include <QJsonObject>
#include <QNetworkAccessManager>
#include <QNetworkRequest>
#include <QNetworkReply>
#include <QScriptEngine>
#include <QThread>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    void service();

signals:

public slots:

private slots:
    void on_btnSend_clicked();
    void onResult(QNetworkReply*);

private:
    Ui::MainWindow *ui;
    QNetworkRequest request;
    QNetworkAccessManager netmanager;
    QNetworkReply *currentReply;

};

#endif // MAINWINDOW_H
