servoy-com-plugin
=================

A COM plugin for Servoy

Original source: https://www.servoyforge.net/projects/servoy-com

Developers must install the `jacob-1.18-M2-x86.dll` into their `C:\WINDOWS\system32` folder.  Clients will automatically have his file loaded from java webstart.
Developers must also install the Visual C++ runtime at http://download.microsoft.com/download/e/1/c/e1c773de-73ba-494a-a5ba-f24906ecf088/vcredist_x86.exe

Note:  The jacob DLL file bundled with this plugin is the x86 version.  If your system requires 64 bit, you can download from the link below.
 - http://sourceforge.net/projects/jacob-project/

Build Instructions:

There should be 3 jars generated:

 1. `servoyguy_servoycom.jar`: should bundle `com.servoyguy.plugins.servoycom` & `com.servoyguy.plugins.servoycom.images`
 2. `servoyguy_servoycom/jacob.jar`:  the jacob library
 3. `servoyguy_servoycom/windows_native.jar`:  should bundle `com.servoyguy.plugins.resources`
		Note: the windows_native jar wraps the jacob DLL file so it will get streamed through the JNLP and downloaded to the client. The DLL then gets unwrapped and installed on the client.
