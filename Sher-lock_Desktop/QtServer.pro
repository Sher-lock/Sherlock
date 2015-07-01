#-------------------------------------------------
#
# Project created by QtCreator 2015-03-25T20:44:50
#
#-------------------------------------------------

QT       += core gui
QT       += network
QT       += websockets
QT       += script
QT       += core network

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = QtServer
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    backprocess.cpp \
    src/emailaddress.cpp \
    src/mimeattachment.cpp \
    src/mimefile.cpp \
    src/mimehtml.cpp \
    src/mimeinlinefile.cpp \
    src/mimemessage.cpp \
    src/mimepart.cpp \
    src/mimetext.cpp \
    src/smtpclient.cpp \
    src/quotedprintable.cpp \
    src/mimemultipart.cpp \
    src/mimecontentformatter.cpp \

HEADERS  += mainwindow.h \
    backprocess.h \
    src/emailaddress.h \
    src/mimeattachment.h \
    src/mimefile.h \
    src/mimehtml.h \
    src/mimeinlinefile.h \
    src/mimemessage.h \
    src/mimepart.h \
    src/mimetext.h \
    src/smtpclient.h \
    src/SmtpMime \
    src/quotedprintable.h \
    src/mimemultipart.h \
    src/mimecontentformatter.h \
    src/smtpexports.h


FORMS    += mainwindow.ui
