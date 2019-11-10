import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

class GwtLauncherExtension {
    final RegularFileProperty launcherFile
    final Property<String> modules
    
    GwtLauncherExtension(Project project) {
        launcherFile = project.objects.fileProperty()
        modules = project.objects.property(String)
        
        launcherFile.set(project.file(project.name + '-codeserver.launch'))
    }
}
