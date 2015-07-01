#ifndef BACKPROCESS_H
#define BACKPROCESS_H

#include <QObject>
#include<QtCore>

class backprocess : public QThread
{
    Q_OBJECT
public:
     explicit backprocess(QObject *parent = 0);
    ~backprocess();
    void run();

signals:
    void receive();

public slots:

};

#endif // BACKPROCESS_H
