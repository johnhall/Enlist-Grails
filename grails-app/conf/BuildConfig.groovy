grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.server.port.http=8090
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
        //if(Environment.current == Environment.PRODUCTION) { excludes "grails-plugin-log4j", "   log4j" }
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		
		// For CloudFoundry plugin
		mavenRepo "http://maven.springframework.org/milestone/"
		
    }
    dependencies {
        //if(Environment.current == Environment.PRODUCTION) { runtime "org.slf4j:slf4j-jdk14:1.6.4" }

        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.18'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"

        runtime ':twitter-bootstrap:2.1.0'
        runtime ':fields:1.3'

        compile ":spring-security-core:1.2.7.3"
		compile ":spring-security-ui:0.2"
		compile ":jquery-ui:1.8.24"
		compile ":famfamfam:1.0.1"
		// if not commented out, can not create war
		//compile ":cache-headers:1.1.5"
		
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.1"

		compile ":build-test-data:2.0.3"
		compile ":cache:1.0.0"
		compile ":mail:1.0"
		compile ":quartz:0.4.2"
		compile ":quartz-monitor:0.2"
        compile ":searchable:0.6.4"
        compile ":inflector:0.2"
        compile ":rollback-on-exception:0.1"

        test ":spock:0.7"

    }
}
//grails.war.resources = { stagingDir, args ->
//    copy(file: "logging.properties",
//            tofile: "${stagingDir}/WEB-INF/classes/logging.properties")
//}
