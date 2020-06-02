[![Build Status](https://travis-ci.com/OHNLP/MedTagger.svg?branch=master)](https://travis-ci.com/OHNLP/MedTagger)

# MedTagger

MedTagger contains a suite of programs that the Mayo Clinic NLP program has developed in 2013.
It includes three major components: MedTagger for indexing based on dictionaries, MedTaggerIE for
information extraction based on patterns, and MedTaggerML for machine learning-based named entity recognition.

# Build and run

Build a docker image

```
docker build -t medtagger_web:1.0 .S
```

Run a container from the image
```
docker run -p 80:80 medtagger_web:1.0
```

Then, the web app can be accessed via web on localhost (i.e. 127.0.0.1).

# Custom Ruleset Use Case - COVID 19 
MedTagger IE Pipelines use a custom ruleset format. An example ruleset of Coronavirus Diseases 19 (COVID 19) related symptoms (e.g. dry cough, fever, fatigue) S
can be found under the `/src/main/resources/medtaggerieresources/covid19` directory. These resources are what tells MedTagger
what to do/extract, and this directory is expected as input for the RULEDIR parameter 


# For Developers
1. Clone this repository
2. You will need JDK8 or above, Apache Maven, and Apache Ant installed
3. When your modifications are complete, from the project root directory:
    - Run `mvn clean install`
    - Run `ant dist`
    - A distribution zip will be created at `MedTagger.zip` in the root directory
    
   
# Reference
Liu H, Bielinski SJ, Sohn S, Murphy S, Wagholikar KB, Jonnalagadda SR, Ravikumar KE, Wu ST, Kullo IJ, Chute CG. An information extraction framework for cohort identification using electronic health records. AMIA Summits on Translational Science Proceedings. 2013;2013:149.

Wen A, Fu S, Moon S, El Wazir M, Rosenbaum A, Kaggal VC, Liu S, Sohn S, Liu H, Fan J. Desiderata for delivering NLP to accelerate healthcare AI advancement and a Mayo Clinic NLP-as-a-service implementation. npj Digital Medicine. 2019 Dec 17;2(1):1-7.
