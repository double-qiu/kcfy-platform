package com.kcfy.platform.server.kernal.support;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.server.kernal.Constants;
import com.kcfy.platform.server.kernal.model.JFile;

/**
 *
 * Created by xiongp on 2016/3/14.
 */
public class FileHelper {
	
	public static final Logger logger = LoggerFactory.getLogger(FileHelper.class);

    /**
     * 文件转换成流
     * @param file
     * @return
     */
    public static byte[] toBytes(File file){
        byte[] bytes = null;
        InputStream inputStream = null;
        try {
        	inputStream = new FileInputStream(file);
        	bytes = IOUtils.toByteArray(IOUtils.toBufferedInputStream(inputStream));
        } catch (IOException e) {
        	throw new IllegalArgumentException("文件出错",e);
		}finally {
            IOUtils.closeQuietly(inputStream);
        }
        return bytes;
    }
    
    /**
	 * 保存文件异步
	 * @param jfile
	 */
    public static JFile writeFileToDiskAsyn(final JFile jfile,final String absolutePath){
    	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
				try {
					writeFileToDisk(jfile,absolutePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        return jfile;
    }
    /**
	 * 保存文件
	 * @param jfile
	 * @throws IOException
	 */
    public static JFile writeFileToDisk(JFile jfile,String absolutePath) throws IOException {
        String relativePath = wrapRelativePath(jfile.getRelativePath());
        String fileExtension = appendIfMissingDot(jfile.getFileExtension());
        jfile.setExpectedFullFileName(UUID.randomUUID().toString()+fileExtension);
        jfile.setRelativePath(relativePath);
        String dirPath = wrapAbsolutePath(absolutePath)+relativePath;
        String fileName = jfile.getExpectedFullFileName();
        logger.debug("dirPath: "+dirPath);
        logger.debug("fileName: "+fileName);
        byte[] fileContent = jfile.getFileContent();
        writeBytesToDisk(fileContent, dirPath, fileName);
        return jfile;
    }
    
    public static void writeBytesToDisk(byte[] fileContent,String dirPath,String fileName) throws IOException {
    	FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File dir = new File(dirPath);
        File file = new File(dirPath+fileName);
        logger.debug("dirPath: "+dirPath);
        logger.debug("filePath: "+dirPath+fileName);
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();
        }
        try {
        	logger.debug("prepared writting : "+dirPath+fileName);
        	if(!file.exists()){
        		logger.debug("prepared creating new file  : "+dirPath+fileName);
        		file.createNewFile();
        	}
        	logger.debug("new file created : "+dirPath+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileContent);
            bos.flush();
            fos.flush();
            logger.debug("file created (1) : "+dirPath+fileName);
            logger.debug("file created (2) : "+file.getAbsolutePath());
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(bos);
        }
    }
    
    /**
     * 确保绝对路径以slash结尾
     * @param absolutePath
     * @return
     */
    public static String wrapAbsolutePath(String absolutePath){
		if(!absolutePath.endsWith(Constants.SLASH)){
			absolutePath = absolutePath + Constants.SLASH;
		}
		return absolutePath;
	}

    /**
     * 确保相对路径不以slash开头以slash结尾
     * @param relativePath
     * @return
     */
	public static String wrapRelativePath(String relativePath) {
		if (StringUtils.isBlank(relativePath) || Constants.SLASH.equals(relativePath)) {
			relativePath = StringUtils.defaultString(relativePath);
		} else {
			relativePath = StringUtils.appendIfMissing(relativePath, Constants.SLASH);
			if (StringUtils.startsWith(relativePath, Constants.SLASH)) {
				relativePath = relativePath.substring(1);
			}
		}
		return relativePath;
	}
	
	public static String appendIfMissingDot(String fileExtension){
		if(StringUtils.isBlank(fileExtension)){
        	fileExtension = StringUtils.defaultString(fileExtension);
        }else{
        	fileExtension = Constants.DOT+fileExtension;
        }
		return fileExtension;
	}

    public static void main(String[] args) throws IOException {
        JFile jFile = new JFile();
        jFile.setRelativePath("img/");
        jFile.setExpectedFullFileName("1.txt");
        jFile.setFileExtension("jpg");
        jFile.setFileNameNoExtension("1");
        byte[] buffer = null;
        try {
            File f = new File("c:/usr/test.txt");
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        jFile.setFileContent(buffer);
        writeFileToDisk(jFile,"C:/usr/filePath");
    }
}
