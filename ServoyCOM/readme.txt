Developers must install the jacob-1.14.3-x86.dll into their C:\WINDOWS\system32 folder.  Clients will automatically have his file loaded from java webstart.
Developers must also install the Visual C++ runtime at http://download.microsoft.com/download/e/1/c/e1c773de-73ba-494a-a5ba-f24906ecf088/vcredist_x86.exe

Note:  The jacob DLL file bundled with this plugin is the x86 version.  If your system requires 64 bit, you can download from the links below.
http://sourceforge.net/projects/jacob-project/
http://jacob-project.wiki.sourceforge.net/

Build Instructions:
There should be 3 jars generated:
	1. servoyguy_servoycom.jar: should bundle com.servoyguy.plugins.servoycom & com.servoyguy.plugins.servoycom.images
	2. servoyguy_servoycom/jacob.jar:  the jacob library
	3. servoyguy_servoycom/windows_native.jar:  should bundle com.servoyguy.plugins.resources
		Note: the windows_native jar wraps the jacob dll file so it will get streamed through the JNLP and downloaded to the client.  The DLL then gets unwrapped and installed on the client.