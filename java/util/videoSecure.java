// TODO Auto-generated method stub
		byte XOR = 0X79;
        MappedByteBuffer buffer=null;
        try {
            File file = new File("E:/FFOutput/015-final.mp4");
            if(file.exists()&&file.isFile()){
                
                long size = file.length();

                buffer=new RandomAccessFile("E:/FFOutput/015-final.mp4","rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 10);
                int sum=0;
                int n;
                long t1=System.currentTimeMillis();
                for(int i=0;i<10;i++){
                   byte src=   buffer.get(i);
                   System.out.println("before :"+src);
                   src = (byte)(src^XOR);
                   System.out.println("after :"+src);
                   buffer.put(i,src);//修改Buffer中映射的字节的值   
                    
                }
                buffer.force();
                buffer.clear();
                
                long t=System.currentTimeMillis()-t1;
                System.out.println("sum:"+sum+"  time:"+t);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
