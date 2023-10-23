package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.*;
import java.util.List;

public abstract class BaseBuild {
    protected static final String SUFFIX_JAVA = ".java";

    /**
     * 生成的文件路径
     */
    private String path;
    /**
     * 生成的文件名后缀
     */
    private String fileNameSuffix;

    public BaseBuild(String path, String fileNameSuffix) {
        this.path = path;
        this.fileNameSuffix = fileNameSuffix;
    }

    public abstract void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException;

    public final void execute(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(tableInfo -> {
            doExecute(tableInfo, path, tableInfo.getBeanName() + fileNameSuffix);
        });
    }

    protected void doExecute(TableInfo tableInfo, String path, String fileName) {
        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();
        File file = new File(folder, fileName);
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        OutputStreamWriter osw = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            construct(tableInfo, bw);
            bw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != bw) bw.close();
                if (null != fos) fos.close();
                if (null != osw) osw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
