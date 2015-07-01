#include "backprocess.h"

backprocess::backprocess(QObject *parent) : QThread(parent)
{

}

backprocess::~backprocess()
{

}

void backprocess :: run()
{
    while(true)
    {
        emit receive();
        this->sleep(1);
    }

}
