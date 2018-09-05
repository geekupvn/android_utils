## GEEKUp Android Utils
[![](https://jitpack.io/v/geekupvn/android_utils.svg)](https://jitpack.io/#geekupvn/android_utils)

How to use </br>
To get a GEEKUp Android Utils into your project:

Step 1. Add the JitPack repository to your build file </br>
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	   	implementation 'com.github.geekupvn:android_utils:master'
	}

Step 3. Initialize
  
```java
public class MyApplication extends Application {
	@Override
    	public void onCreate() {
        	super.onCreate();
		ScreenUtils.init(context);
		// or
		StringUtils.init(context);
	}
}
```
