import os
import shutil

def copy_and_rename(path,dst): 
	'''Param :
	path = Folder source
	dst = Folder destination

	Return: 
	create a copy and rename them to the destination folder
	'''
	dir_ = os.listdir(path)
	for d in dir_:
		dfolder = os.path.join(path,d)
		file = os.listdir(dfolder)
		for f in file:
			src = os.path.join(dfolder,f)
			new_name = d+f
			shutil.copyfile(src,dst+new_name)

path = "/home/rizqi/Downloads/bangkit/capstone-project/TEST/test"
dst = "/home/rizqi/Downloads/bangkit/capstone-project/TEST/new_test/"
copy_and_rename(path,dst)