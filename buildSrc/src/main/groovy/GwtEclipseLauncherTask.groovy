import groovy.text.SimpleTemplateEngine
import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

@CompileStatic
class GwtEclipseLauncherTask extends DefaultTask {
    static String DEFAULT_GWT_ARGS = '-logLevel INFO -port 9876 -launcherDir build/gwt/launcherDir -XmethodNameDisplayMode ONLY_METHOD_NAME -sourceLevel 10 -style PRETTY'
    
    @OutputFile
    final RegularFileProperty launcherFile = project.objects.fileProperty()
    @Input
    final Property<String> modules = project.objects.property(String)

    @TaskAction
    void generate() {
        String template = getClass().getClassLoader().getResource('GwtCodeServerLauncher.tmpl.xml').text
        SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()
        String gwtArgs = DEFAULT_GWT_ARGS
        
        ExtensionAware gwtExtension = (ExtensionAware) project.extensions.findByName('gwt')
        if(gwtExtension != null) {
            def jsInteropExports = gwtExtension.properties.jsInteropExports
            if(jsInteropExports != null) {
                def shouldGenerate = jsInteropExports.invokeMethod('shouldGenerate', null)
                if(shouldGenerate) {
                    gwtArgs += ' -generateJsInteropExports'
                }
            }
        }
        
        gwtArgs += ' ' + modules.get()
        def templateBindings = [
            gwtArgs: gwtArgs,
            projectName: project.name,
        ]
        String launcherContent = templateEngine.createTemplate(template).make(templateBindings).toString()
        launcherFile.get().asFile.withWriter('utf-8', {writer ->
            writer.write launcherContent
        })
    }
}
