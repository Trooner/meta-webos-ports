WEBOS_PORTS_GIT_REPO ?= "git://github.com/webOS-ports"
WEBOS_PORTS_GIT_REPO_COMPLETE ?= "${WEBOS_PORTS_GIT_REPO}/${WEBOS_REPO_NAME}${WEBOS_GIT_TAG}${WEBOS_GIT_BRANCH}"

inherit webos_public_repo
