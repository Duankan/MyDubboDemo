package com.github.Duankan.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将文件夹下面的文件
 * 打包成zip压缩文件
 *
 * @author admin
 */
public final class FileToZip {

    private FileToZip() {
    }

    /**
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径
     * @param fileName       :压缩后文件的名称
     * @return true/false
     * @desc 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     * 1.先判断源文件是否存在
     * 2.判断要压缩的zip路径是否存在
     * 3.判断打包的是文件还是文件夹
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
                } else {
                    //如果要压缩的是文件
                    if (sourceFile.isFile()) {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024 * 10];
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFile);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                    }
                    //压缩的是文件夹
                    else {
                        File[] sourceFiles = sourceFile.listFiles();
                        if (null == sourceFiles || sourceFiles.length < 1) {
                            System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                        } else {
                            fos = new FileOutputStream(zipFile);
                            zos = new ZipOutputStream(new BufferedOutputStream(fos));
                            byte[] bufs = new byte[1024 * 10];
                            for (int i = 0; i < sourceFiles.length; i++) {
                                //创建ZIP实体，并添加进压缩包
                                ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                                zos.putNextEntry(zipEntry);
                                //读取待压缩的文件并写进压缩包里
                                fis = new FileInputStream(sourceFiles[i]);
                                bis = new BufferedInputStream(fis, 1024 * 10);
                                int read = 0;
                                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                                    zos.write(bufs, 0, read);
                                }
                            }
                            flag = true;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                //关闭流
                try {
                    if (null != bis) bis.close();
                    if (null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\ljiu\\Desktop\\qq2.png";
        String zipFilePath = "C:\\Users\\ljiu\\Desktop";
        String fileName = "12700153file";
        boolean flag = FileToZip.fileToZip(sourceFilePath, zipFilePath, fileName);
        if (flag) {
            System.out.println("文件打包成功!");
        } else {
            System.out.println("文件打包失败!");
        }
    }

}
