/*
 * Copyright (c) 2013, 2014 Institute for Internet Security - if(is)
 *
 * This file is part of Hermes Malware Analysis System.
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the
 * Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl 5
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 */

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = ["file:/etc/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false


// application config
hermes {
    minNodeStatusData = 50 // the number of entries which is kept after cleanup
}

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://localhost:8080/Hermes"

        grails.plugin.databasemigration.updateOnStart = true
        grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']
        
        hermes {

//            dirUpload = "/tmp/hermesUpload/"
            dirUpload = '.' + '/web-app/upload/'
            urlDownload = grails.serverURL + '/upload/'
        }


    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "https://hermers-server.com/Hermes"

        grails.plugin.databasemigration.updateOnStart = true
        grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']

        hermes {
            dirUpload = "/var/hermes/upload/"
//            dirUpload = System.properties.getProperty('catalina.base') + '/webapps/Hermes/upload/' //
            //            dirUpload = '.' + '/upload/'
            urlDownload = grails.serverURL + '/upload/'
        }


    }
    
    test {       
        hermes {
            dirUpload = '.' + '/web-app/upload-test/'
            urlDownload = '/upload-test/'
        }
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    debug 'grails.app.jobs'
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'net.ifis.ites.hermes.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'net.ifis.ites.hermes.security.UserRole'
grails.plugin.springsecurity.authority.className = 'net.ifis.ites.hermes.security.Role'
grails.plugin.springsecurity.password.algorithm='SHA-512'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/jobstatus/'
grails.plugin.springsecurity.securityConfigType = grails.plugin.springsecurity.SecurityConfigType.InterceptUrlMap
grails.plugin.springsecurity.interceptUrlMap = [
    '/' : ['permitAll'],
    '/index' : ['permitAll'],
    '/index.gsp' : ['permitAll'],
    '/**/js/**' : ['permitAll'],
    '/**/css/**' : ['permitAll'],
    '/**/assets/**' : ['permitAll'],
    '/**/images/**' : ['permitAll'],
    '/**/favicon.ico' : ['permitAll'],
    '/**/upload/**' : ['permitAll'],
    '/logout/**' : ['permitAll'],
    '/login/**' : ['permitAll'],
    '/login/denied' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED'],
    '/administration/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED'],
    '/jobstatus/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED'],
    '/node/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED'],
    '/publishing/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED','hasAnyRole("ROLE_API","ROLE_SUPERUSER")'],
    '/hypervisor/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_HYPERVISORMANAGEMENT","ROLE_SUPERUSER")'],
    '/operatingSystem/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_OSMANAGEMENT","ROLE_SUPERUSER")'],
    '/sample/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_SAMPLEMANAGEMENT","ROLE_SUPERUSER")'],
    '/sensor/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_SENSORMANAGEMENT","ROLE_SUPERUSER")'],
    '/virtualMachine/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_VMMANAGEMENT","ROLE_SUPERUSER")'],
    '/job/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_JOBMANAGEMENT","ROLE_SUPERUSER")'],
    '/user/**' : ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED', 'hasAnyRole("ROLE_USERMANAGEMENT","ROLE_SUPERUSER")']
]

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
