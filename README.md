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
