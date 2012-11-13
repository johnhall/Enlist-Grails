// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = 'enlist.grails' // change this to alter the default package name and Maven publishing destination
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

grails.mail.default.from="enlistappg48@gmail.com"
grails {
//    grails.mail.default.from="imms.noreply@gmail.com"
    mail {
        host = "smtp.gmail.com"
				username = "enlistappg48@gmail.com"
				password = "Grails48Hack"
        port = 587
        props = ["mail.smtp.auth":"true",
                "mail.smtp.socketFactory.port":"587",
                "mail.smtp.starttls.enable": "true",
                "mail.smtp.socketFactory.fallback":"false"]
//                // it's not recommended to use 465.
//                port = 465
//				props = ["mail.smtp.auth":"true",
//					"mail.smtp.socketFactory.port":"465",
//					"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
//					"mail.smtp.socketFactory.fallback":"false"]
    }
}
environments {
    development {
        grails.logging.jul.usebridge = true
        // mail plugin unable to pickup this configuration if we put inside the env specific clause.
//		grails {
//			mail {
////				host = "smtp.gmail.com"
////				username = "enlistappg48@gmail.com"
////				password = "Grails48Hack"
////                port = 465
////				props = ["mail.smtp.auth":"true",
////					"mail.smtp.socketFactory.port":"465",
////					"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
////					"mail.smtp.socketFactory.fallback":"false"]
//			}
//		}
    }
    production {
        grails.logging.jul.usebridge = false
	}
}

// log4j configuration
log4j = {
	// Example of changing the log pattern for the default console appender:
	//
	//appenders {
	//    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	//}
	info   'AuthenticationEvents', "buildtestdata"

	debug  'grails.app'

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
		'net.sf.ehcache.hibernate',
            'grails.app.services.org.grails.plugin.resource',
            'grails.app.taglib.org.grails.plugin.resource',
            'grails.app.resourceMappers.org.grails.plugin.resource'
}

// Fix grails taglib g:paginate to work with bootstrap css.
grails.plugins.twitterbootstrap.fixtaglib = true

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'enlist.grails.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'enlist.grails.UserRole'
grails.plugins.springsecurity.authority.className = 'enlist.grails.Role'

grails.sitemesh.default.layout= 'bootstrap'

rule {
    activity {
        allowRegistrationAfterEndDate = false

        generateReminderJobForNext = 1   // day(s) from the moment batch job run.
    }
}
batch {
    // run every 6 hours?
    ActivityPointJob = "0 0 0/6 * * ?"
    ActivityReminderJob = "0 0 1/6 * * ?"
}

grails.gorm.failOnError=true
bootstrap.cleanupData = true
bootstrap.createSampleData = true
environments {
    // override this
    development {
        rule.activity.allowRegistrationAfterEndDate = true
        // run every 5 minutes?
        batch.ActivityPointJob = "0 0/5 * * * ?"
        batch.ActivityReminderJob = "0 1/5 * * * ?"
    }
    // only for appFog
    production {
        rule.activity.allowRegistrationAfterEndDate = true
        // run every 5 minutes?
        batch.ActivityPointJob = "0 0/5 * * * ?"
        batch.ActivityReminderJob = "0 1/5 * * * ?"
        searchable {       
        	//"enlist' => Error (JSON 500): Internal Server Error 	
        	//"/logs" => Error: Application [enlist] failed to start, logs information below.

		    compassConnection = new File("index").absolutePath
		    bulkIndexOnStartup = false
        }
    }
    test {
        // make sure it's clean before run integration test
        bootstrap.cleanupData = true
        bootstrap.createSampleData = false
    }
}
