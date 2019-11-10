import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

class SpringBootLauncherExtension {
    final RegularFileProperty launcherFile
    final Property<String> mainClass
    final Property<String> profiles
    
    SpringBootLauncherExtension(Project project) {
        launcherFile = project.objects.fileProperty()
        mainClass = project.objects.property(String)
        profiles = project.objects.property(String)
        
        launcherFile.set(project.file(project.name + '-server.launch'))
        profiles.set('')
    }
}
