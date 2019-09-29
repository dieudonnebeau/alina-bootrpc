package com.alina.bootrpc.common.core.exception.file;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:31
 * @description：文件名称超长限制异常类
 * @modified By：
 * @version:     1.0
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
