DESCRIPTION = "Say hello to the world with mylib"
SECTION = "console/utils"
LICENSE = "MIT"
SRCREV = "1.0"

# Should be changed accordingly to the license.
#LIC_FILES_CHKSUM = "file://COPYING;md5=60f419914bcc44d1224a21e6f3c18ac8"

SRC_URI = "file://hello-world.c \
           file://COPYING"

S = "${WORKDIR}"
BBCLASSEXTEND = "native"

LDFLAGS = "-Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed"
TARGET_CC_ARCH += "${LDFLAGS}"

do_compile[depends] += "mylib:do_install"

do_compile () {
	${CC} -o hello-world hello-world.c -lmylib
}

do_install () {
	install -d ${D}/${bindir}
	install -m 755 hello-world ${D}/${bindir}/hello-world
}

