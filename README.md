# manukku-birds-classification

We use a dataset from https://www.kaggle.com/gpiosenka/100-bird-species and we select 44 species of birds that are distributed in Indonesia and its surroundings

For the machine learning model, we use InceptionResnetV2 architecture and add some additional layer.

We use image_data_generator to generate our image dataset with augmentation (for training dataset). At first we try with InceptionResNetV2 preprocess_input, but when we test the model, we got bad result, that is around 2% accuracy (We got 98% accuracy when training). Then we use ResNet50 preprocess_input and get better testing result, that is 85% accuracy.
