# Manukku - Birds Classification

In Indonesia there are 1771 bird species, 562 are protected, and 31 of them are categorized as critical endangered. In addition, the number of protected animals and plants in Indonesia also increased from 1999 to 2018 by 26%. One of the factors that causes this, is the lack of public knowledge about the types of protected animals and plants.

Here we offer a solution in the form of an application called “Manukku”. Currently, we have 3 main features in Manukku App, that is "Detect", "List", and "Forum" features. “Detect” feature is used if we want to know the type of bird that we see or we have. We can input image from camera or gallery. Then the apps will identify it with tensorflow lite model deployed in the Apps. The “List” feature is used to see the information about the bird such as scientific name, conservation status, and other descriptions. These data is deployed in the cloud. The “Forum” feature is used as a place for discussion or a forum for bird lovers. We can also use it to submit reports in case of illegal acts against birds.

Our goal is to educate the public regarding the types of birds, so as to reduce the number of illegal bird trade.

## Machine Learning
We use a dataset from https://www.kaggle.com/gpiosenka/100-bird-species and we select 44 species of birds that are distributed in Indonesia and its surroundings

For the machine learning model, we use InceptionResnetV2 architecture and add some additional layer.

We use imageDataGenerator to generate our image dataset with augmentation (for training dataset). For the prerocess_input, at first we try with InceptionResNetV2 preprocess_input, but when we test the model, we got bad result, that is around 2% accuracy (We got 98% accuracy when training). Then we use ResNet50 preprocess_input and get better testing result, that is 88% accuracy.

For the testing image, we make a program, so its can display the True name, Predict name, and its accuracy. The input for that program is images that have name : "label+image_number". So we create a simple python code "copy_and_rename.py" for rename the image to "label+image number" (Folder name+image name). We get 88% accuracy for this testing.

And then we convert the model to tensorflow lite format, so its can deploy on Android Apps.

We also test that tensorflow lite model, and get 85% accuracy.

## Android

### Detection
how the detection system in this application works, first we embed the Machine Learning model in the form of a .tflite file into the Android program, then in the Android program, the program will first ask the user to enter input in the form of photos, either through the camera or gallery, then the photos that are displayed. selected will be used as input for the Machine Learning model, then the photo will be processed, the results obtained from the detection process by Machine Learning will be in the form of a string, the result of this string will be used to perform a query process to the server to load detailed bird data then the data will be displayed to the user interface .
Detection can only be done if the uploaded photo is set to 1:1

### List
In the list section, we store a list of bird names, and photos in the Android program (Local) and then display them as a list, when the user selects a list, the program will retrieve the bird name data, to perform a query process to the database, and then the details of the bird will be shown. For future development, we plan to store photo list data in the database, so that the file size will be smaller.

### Forum
This forum feature cannot be used, because it is not yet fully finished, for further development, this forum feature will be completed and refined.

## Cloud Computing

### Project 
Create gcp project for manukku and firebase project linked to manukku project in gcp

### Budget & Alert
Create a budget & alert so that our gcp project does not exceed the available budget

### IAM & Admin
Create a role for each team member so that they can access the gcp project and firebase project

### Database
Create a database using cloud firestore(details of the birds) and cloud storage(images of the birds). Manually input the bird details for list in cloud firestore and the bird pictures for list in cloud storage

### Database Rules
Create a rule to allow the application to read the database but require access to write

### API
Provide API key for firebase service for Android team so that they can access the required data in the database.

### Cloud Function
Create a function for cloud Function to automatically deploy model if you upload tflite model in cloud storage (the function was executed successfully but the model is larger than the 40 mb limit so the custom model created by the function does not contain anything inside)

### Deploy Link fo Manukku apk
Make deploy link for apk from manukku using app distribution. After you click the link, you will be directed to the website to register using an email with the @bangkit.academy domain so you can test the Manukku application. After that our team will add the email as a tester and will invite the email to be a tester. If you click get started and agree to become a tester and follow the steps in the email, then you will be able to download the manukku app
