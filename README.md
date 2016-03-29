# Java-Speech-Translate

Simple speech translation websocket client which communicates with Microsoft Translator speech translation service.

The sample stream an audio file (PCM 16bit 16kHz mono WAV) to the server and prints out the "Final text" from the server in console.

A sample file is provided "HelloWorld.wav"

You will need to setup a subscription with Microsoft Translator. [Click Here] (https://www.microsoft.com/en-us/translator/default.aspx) to get started.

Speech API documentation can be [found here.] (https://docs.microsofttranslator.com/)


## Setup
Java 1.7+ and Maven 3+

To install, navigate to the root directory (containing pom.xml) and execute

mvn clean install

this will download the dependencies

To get the list of languages

java -cp target\Java-Speech-Translate-1.0.0.jar;target\dependency-jars\* GetLanguages

To stream an audio file to our speech translate service

java -cp target\Java-Speech-Translate-1.0.0.jar;target\dependency-jars\* SpeechWebSocketClient [ClientId] [ClientSecret] [AudioFile]


## Dependencies
The application has dependency on following external libraries, which are configured in pom.xml

[Jetty] (http://www.eclipse.org/jetty/) For lightweight websocket client api

[GSON] (https://github.com/google/gson)Deserialize json response
