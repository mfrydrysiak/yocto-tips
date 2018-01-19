DESCRIPTION = "Static library mylib"
SECTION = "console/utils"
LICENSE = "MIT"
SRCREV = "1.0"

PR = "r0"

# Should be changed accordingly to the license.
#LIC_FILES_CHKSUM = "file://COPYING;md5=60f419914bcc44d1224a21e6f3c18ac8"

SRC_URI = "file://mylib.c \
           file://mylib.h \
           file://COPYING"

S = "${WORKDIR}"
BBCLASSEXTEND = "native"

PACKAGES = "{PN} {PN}-dev {PN}-dbg ${PN}-staticdev"

RDEPENDS_${PN}-staticdev = ""
RDEPENDS_${PN}-dev = ""
RDEPENDS_${PN}-dbg = ""

FILES_${PN}-staticdev += "${includedir}/mylib.h"

do_compile () {
	${CC} -c mylib.c -o mylib.o -I.
	${AR} rcs libmylib.a mylib.o
}

do_install () {
	install -d ${D}${libdir}
	install -d ${D}${includedir}
	install -m 0644 libmylib.a ${D}${libdir}
	install -m 0644 mylib.h ${D}${includedir}
}

