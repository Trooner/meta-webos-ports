SUMMARY = "Qt QPA plugin for Open webOS offscreen rendering"
SECTION = "webos/libs"
LICENSE = "GPL-3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "0.1.0+gitr${SRCPV}"
SRCREV = "f0283c9148a7e070f0e042085c92ba003acc5443"

DEPENDS = "qtbase qtdeclarative qtwebkit luna-sysmgr-ipc luna-sysmgr-ipc-messages libwebos-gui"

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

inherit qmake5
inherit webos_ports_repo

# Set path of qt5 headers as qmake5_base.bbclass sets this to just ${includedir} but
# actually it is ${includedir}/qt5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

FILES_${PN} += "${libdir}/${QT_DIR_NAME}/plugins/platforms"
FILES_${PN}-dbg += "${libdir}/${QT_DIR_NAME}/plugins/platforms/.debug"
