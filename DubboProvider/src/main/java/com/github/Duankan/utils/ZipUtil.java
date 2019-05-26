package com.github.Duankan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author duankang
 * @功能 将文件压缩zip
 * @date 2019-05-18
 * @desc
 */
public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;

    public static void toZip(String srcDir, OutputStream outputStream, boolean keepDirStruct) {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(outputStream);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), keepDirStruct);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStruct) {
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            if (sourceFile.isFile()) {
                //向zip输出流中添加一个zip实体
                zos.putNextEntry(new ZipEntry(name));
                //copy文件到zip输出流中
                int len;
                FileInputStream fis = new FileInputStream(sourceFile);
                while ((len = fis.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.flush();
                zos.closeEntry();
                fis.close();
            } else {
                File[] listFiles = sourceFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    //需要保留原来的文件结构时，需要对文件夹进行处理
                    if (KeepDirStruct) {
                        zos.putNextEntry(new ZipEntry(name + "/"));
                        zos.closeEntry();
                    }
                } else {
                    for (File file : listFiles) {
                        //判断是否需要保留原来的文件结构
                        if (KeepDirStruct) {
                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                            compress(file, zos, name + "/" + file.getName(), KeepDirStruct);
                        } else {
                            compress(file, zos, file.getName(), KeepDirStruct);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
