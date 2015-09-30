import os
import re
import shutil
import sys

#�����ļ����������ļ���·��
def listfile(dirpath)
    fileList=[]
    for root,dirs,files in os.walk(dirpath)
        for fileObj in files
            fileList.append(os.path.join(root,fileObj))
    return fileList

#��utf-8���ļ��������ж�ȡ�ļ�
#ʹ��������ʽ��ƥ��
def replaceFile(filedir,pattern,strTo)
    fileList=listfile(filedir)
    for fileObj in fileList
        #�Զ�д����ʽ�Լ�utf-8���뷽ʽ���ļ�
        f=open(fileObj,'r+',encoding= 'utf-8')
		#������
        all_the_lines=f.readlines()
        #�ص���ʼλ��
		f.seek(0)
		#��������ļ�����������д��
        f.truncate()
        for line in all_the_lines
            if line.find(' any list')!=-1
                f.write(line.replace(' any list','')) #ɾ��octopress�е�Ŀ¼���ɴ��롣
            elif line.find('{toc}')!=-1
                f.write(line.replace('{toc}',''))#ɾ��octopress�е�Ŀ¼���ɴ��롣
            elif line.find('description')!=-1
               continue# f.write(line.replace('{toc}',''))#ɾ��yaml�е�description
            elif line.find('categories')!=-1
               f.write(line.replace('categories','tags'))#ɾ��yaml�е�categories
            else
                pattern3 = re.compile('#')
                match3 = pattern3.search(line)
                if match3.group()!=''
                    f.write(line.replace(match3.group(),match3.group()+' ')) #��������`#`��������Ҫ�ӿո�
                else
                    f.write(pattern.sub(strTo,line))#��coderay�滻Ϊcodeblock�����û�ҵ����ͽ�Զ�в���ԭ����λ��
        f.close()

if __name__=='__main__'
    filedir='DIdeaProjectsjstesttest'
    pattern = re.compile(r'coderay') 
    strTo='codeblock'
    replaceFile(filedir,pattern,strTo)