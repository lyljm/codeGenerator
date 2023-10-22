package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.*;
import java.util.List;

public abstract class BaseBuild {
    protected static final String SUFFIX_JAVA = ".java";

    public abstract void execute(List<TableInfo> tableInfoList);

    public abstract void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException;

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
