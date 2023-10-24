package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.*;
import java.util.List;

/**
 * 所有构造的基类
 */
public abstract class BaseBuild {

    protected static final String SUFFIX_JAVA = ".java";
    protected static final String SUFFIX_XML = ".xml";

    /**
     * 生成的文件路径
     */
    private String path;
    /**
     * 生成的文件名后缀
     */
    private String fileNameSuffix;

    /**
     * 继承该类必须赋值文件路径和文件后缀
     * @param path
     * @param fileNameSuffix
     */
    public BaseBuild(String path, String fileNameSuffix) {
        this.path = path;
        this.fileNameSuffix = fileNameSuffix;
    }

    /**
     * 实际向文件中写入信息
     * @param tableInfo
     * @param bw
     * @throws IOException
     */
    public abstract void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException;

    /**
     * 遍历每个表写入信息
     * @param tableInfoList
     */
    public final void execute(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(tableInfo -> {
            doExecute(tableInfo, path, tableInfo.getBeanName() + fileNameSuffix);
        });
    }

    private void doExecute(TableInfo tableInfo, String path, String fileName) {
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
