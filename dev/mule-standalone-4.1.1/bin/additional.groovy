/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

File wrapperConfigFile = new File(args[0])
jpdaOpts = args[1]

// extracting wrapper conf directory
m = wrapperConfigFile.path =~ /^.*[\\\/]/
m.matches()
String wrapperConfDir = m[0]

File wrapperAdditionalConfFile = new File(wrapperConfDir + 'wrapper-additional.conf')
boolean debugEnabled = args.findIndexOf { '-debug'.equalsIgnoreCase(it)} > -1
boolean adHocOptionsAvailable = args.findIndexOf { it.startsWith('-M') } > -1
boolean wrapperOptionsAvailable = args.findIndexOf { it.startsWith('-W') } > -1

paramIndex = 0

wrapperAdditionalConfFile.withWriter() {

    Writer w ->

    // create the file unconditionally

    w << "#encoding=UTF-8\n"
    w << "# Do not edit this file!\n"
    w << "# This is a generated file to add additional parameters to JVM and Wrapper\n"

    if (debugEnabled || adHocOptionsAvailable) {
        // looking for maximum number of wrapper.java.additional property
        wrapperConfigFile.eachLine {
            String line ->
            switch (line) {
                case ~/^\s*wrapper\.java\.additional\..+/:
                    m = line =~ /^\s*wrapper\.java\.additional\.(\d+).+/
                    m.find()
                    paramIndex = Math.max(Integer.valueOf(m[0][1]), paramIndex)
                    break
            }
        }
        paramIndex++

        if (debugEnabled) {
            writeJpdaOpts(w)
        }

        if (adHocOptionsAvailable) {
            writeAdHocProps(w)
        }

        if (wrapperOptionsAvailable) {
            writeWrapperProps(w)
        }
    }
}


//=== procedure definitions

/**
    Ad-hoc options
*/
def void writeAdHocProps(Writer w) {
    args.findAll { it.startsWith('-M') }.each { arg ->
        w << "wrapper.java.additional.${paramIndex}=\"${arg.replaceFirst("^-M", "")}\"\n"
        w << "wrapper.java.additional.${paramIndex}.stripquotes=TRUE\n"
        paramIndex++
    }
}

/**
    Wrapper options
*/
def void writeWrapperProps(Writer w) {
    args.findAll { it.startsWith('-W') }.each { arg ->
        w << "${arg.replaceFirst("^-W", "")}\n"
    }
}

def void writeJpdaOpts(Writer w) {
    def jvmArgs = []
    jpdaOpts.split("\\s-").each {jvmArgs << it}

    jvmArgs.each {String arg ->
        w << "wrapper.java.additional.${paramIndex++}=-${arg.replaceFirst("^-", "")}\n"
    }
}
