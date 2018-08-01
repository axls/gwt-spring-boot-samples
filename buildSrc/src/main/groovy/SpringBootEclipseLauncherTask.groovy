import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.provider.Property

import groovy.text.SimpleTemplateEngine
import groovy.text.TemplateEngine
import groovy.transform.CompileStatic

@CompileStatic
class SpringBootEclipseLauncherTask extends DefaultTask {
    @OutputFile
    final RegularFileProperty launcherFile = newOutputFile()
    @Input
    final Property<String> mainClass = project.objects.property(String)
    @Input
    final Property<String> profiles = project.objects.property(String)

    @TaskAction
    void generate() {
        String template = getClass().getClassLoader().getResource('SpringBootLauncher.tmpl.xml').text
        SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()
        def templateBindings = [
            mainClass: mainClass.get(),
            mainClassPath: mainClass.get().replaceAll('\\.', '/') + '.java',
            projectName: project.name,
            springBootProfiles: profiles.get(),
        ]
        String launcherContent = templateEngine.createTemplate(template).make(templateBindings).toString()
        launcherFile.get().asFile.withWriter('utf-8', {writer ->
            writer.write launcherContent
        })
    }
}
