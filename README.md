A recorder for mouse and keyboard actions including screenshot and image creation/mangement.

It is intended to keep it as common as possible, but the primary goal is to allow the creation of SikuliX workflows, which in turn can be used to ceate scripts and snippets to be used in SikuliX scripts and programs.

The idea and basic approach is taken from the recorder implementation in [Sikuli Slides](https://github.com/sikuli/sikuli-slides), whose maintainer doubleshow (Tom Yeh) is one of the developers of the original Sikuli.

**Be aware:**
 
Internally the features of [JNativeHook version 2.1.0](https://github.com/kwhat/jnativehook) are used. 
This makes it necessary, to set this package under the **license type GPL 3**.

This means, if you use this feature together with SikuliX, 
then **no longer the MIT license** of SikuliX applies, **but GPL 3** for the whole package
and possible works you build on top. 

This has no effect, as long as you keep SikuliX 
and your works in your private use. 

But **if you distribute** the whole package including 
this JNativeHook package or your works based on this whole package,
 then you **must obey the rules of GPL 3**.