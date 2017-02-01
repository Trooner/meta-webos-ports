# Copyright (c) 2012-2014 LG Electronics, Inc.

DESCRIPTION = "Open webOS component for managing network connections using connman"
AUTHOR = "Sapna Todwal <sapna.todwal@lge.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/services"

DEPENDS = "luna-service2 libpbnjson glib-2.0 luna-prefs openssl glib-2.0-native"
RDEPENDS_${PN} = "connman connman-client"

PV = "1.0.0-11+git${SRCPV}"
SRCREV = "8beac0380411e473b3700932af646e1eb7ce5190"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit webos_ports_fork_repo
inherit webos_cmake
inherit pkgconfig
inherit webos_system_bus
inherit webos_systemd

# Set EXTRA_OECMAKE in webos-connman-adapter.bbappend to override default value for wifi and wired interfaces, for eg.
# EXTRA_OECMAKE += "-DWIFI_IFACE_NAME=wlan0 -DWIRED_IFACE_NAME=eth1"

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
