package com.alina.bootrpc.common.core.exception.file;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:31
 * @description：文件名大小限制异常类
 * @modified By：
 * @version:     1.0
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
