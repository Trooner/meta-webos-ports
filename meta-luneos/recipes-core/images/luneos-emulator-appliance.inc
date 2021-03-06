DESCRIPTION = "LuneOS emulator appliance"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "zip-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/luneos-emulator-appliance:"
SRC_URI = "file://luneos-emulator.ovf"

IMAGE_BASENAME = "luneos"

SRC_VMDK_NAME = "${IMAGE_BASENAME}-image-${MACHINE}${IMAGE_NAME_SUFFIX}.wic.vmdk"
VMDK_NAME     = "${IMAGE_BASENAME}-emulator-${MACHINE}-${IMAGE_VERSION_SUFFIX}.vmdk"
OVF_NAME      = "${IMAGE_BASENAME}-emulator-${MACHINE}-${IMAGE_VERSION_SUFFIX}.ovf"
ZIP_NAME      = "${IMAGE_BASENAME}-emulator-${MACHINE}-${IMAGE_VERSION_SUFFIX}.zip"

do_deploy() {
    if [ ! -e ${DEPLOY_DIR_IMAGE}/${SRC_VMDK_NAME} ] ; then
        bbfatal "Required base image ${DEPLOY_DIR_IMAGE}/${SRC_VMDK_NAME} is not available!"
    fi

    rm -rf ${WORKDIR}/appliance
    mkdir -p ${WORKDIR}/appliance
    sed "s/luneos-emulator-disk.vmdk/${VMDK_NAME}/g" ${WORKDIR}/luneos-emulator.ovf > ${WORKDIR}/appliance/${OVF_NAME}
    sed -i "s/LuneOS Emulator/LuneOS ${IMAGE_VERSION_SUFFIX}/g" ${WORKDIR}/appliance/${OVF_NAME}
    ln -sf ${DEPLOY_DIR_IMAGE}/${SRC_VMDK_NAME} ${WORKDIR}/appliance/${VMDK_NAME}

    (cd ${WORKDIR}/appliance ; zip ${DEPLOY_DIR_IMAGE}/${ZIP_NAME} ${OVF_NAME} ${VMDK_NAME})
}

INHIBIT_DEFAULT_DEPS = "1"
do_deploy[depends] += "${IMAGE_BASENAME}-image:do_image_complete virtual/kernel:do_deploy"

addtask deploy after do_install before do_package
