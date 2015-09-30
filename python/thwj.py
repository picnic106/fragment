import os
import re
import shutil
import sys

#返回文件夹下所有文件的路径
def listfile(dirpath)
    fileList=[]
    for root,dirs,files in os.walk(dirpath)
        for fileObj in files
            fileList.append(os.path.join(root,fileObj))
    return fileList

#以utf-8打开文件，并逐行读取文件
#使用正则表达式来匹配
def replaceFile(filedir,pattern,strTo)
    fileList=listfile(filedir)
    for fileObj in fileList
        #以读写的形式以及utf-8编码方式打开文件
        f=open(fileObj,'r+',encoding= 'utf-8')
		#读出行
        all_the_lines=f.readlines()
        #回到起始位置
		f.seek(0)
		#清空整个文件，便于重新写入
        f.truncate()
        for line in all_the_lines
            if line.find(' any list')!=-1
                f.write(line.replace(' any list','')) #删除octopress中的目录生成代码。
            elif line.find('{toc}')!=-1
                f.write(line.replace('{toc}',''))#删除octopress中的目录生成代码。
            elif line.find('description')!=-1
               continue# f.write(line.replace('{toc}',''))#删除yaml中的description
            elif line.find('categories')!=-1
               f.write(line.replace('categories','tags'))#删除yaml中的categories
            else
                pattern3 = re.compile('#')
                match3 = pattern3.search(line)
                if match3.group()!=''
                    f.write(line.replace(match3.group(),match3.group()+' ')) #代表段落的`#`，后面需要加空格
                else
                    f.write(pattern.sub(strTo,line))#将coderay替换为codeblock，如果没找到，就将远行插入原来的位置
        f.close()

if __name__=='__main__'
    filedir='DIdeaProjectsjstesttest'
    pattern = re.compile(r'coderay') 
    strTo='codeblock'
    replaceFile(filedir,pattern,strTo)