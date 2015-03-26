#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QObject>
#include <QTcpSocket>
#include <QTcpServer>
#include <QDebug>
#include <QBuffer>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
   // void Connect();

signals:

public slots:

private slots:
    void addConnection();
    void processMessage();
    void on_btnSend_clicked();

private:
    Ui::MainWindow *ui;
    QTcpSocket *socket;
    QTcpServer *server;
    QBuffer *buffer;

};

#endif // MAINWINDOW_H
