package com.weitao.collecttest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class DownLoadMusic
{
    static String contextPath = "E://music//";

    static String prefixUrl = "https://music.163.com/song/media/outer/url?id=";

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url)
        throws Exception
    {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet())
        {
            if ("Location".equalsIgnoreCase(key))
            {
                return map.get(key).toArray()[0].toString();
            }

        }
        return "";
    }

    public static void main(String[] args)
        throws Exception
    {
        File music = new File("E://music");
        String suffix = ".mp3";

        if (!music.exists())
        {
            music.mkdir();
        }
        List<Map<Integer, MusicLnfo>> list = fillData();

        for (int i = 0; i < list.size(); i++)
        {
            Iterator<Map.Entry<Integer, MusicLnfo>> iterator = list.get(i).entrySet().iterator();
            while (iterator.hasNext())
            {
                MusicLnfo musicLnfo = iterator.next().getValue();
                String musicUrl = sendGet(musicLnfo.getUrl() + suffix);//得到音乐地址
                String fileName =
                    contextPath + musicLnfo.getTitle() + "-" + musicLnfo.getSinger().replaceAll("\\/", "&") + ".mp3";
                if (!"404".equalsIgnoreCase(musicUrl))
                {
                    downloadSource(musicUrl, fileName);//下载歌曲到本地,并返回绝对路径
                }

            }
        }

    }

    /**
     * 根据远程资源路径，下载资源到本地临时目录
     *
     * @param remoteSourceUrl 远程资源路径
     * @param fileName   文件路径
     */
    private static void downloadSource(String remoteSourceUrl, String fileName)
        throws Exception
    {
        if (fileName.contains("/")) {
            fileName = fileName.replaceAll("\\/", "");
        }
        //下载资源
        File file = new File(fileName);
        System.out.println("fileName=" + fileName);
        if (!file.exists()) {
            file.createNewFile();
            URL url = new URL(remoteSourceUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 10];
            int length = 0;
            while ((length = dataInputStream.read(bytes)) != -1)
            {
                fileOutputStream.write(bytes, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();
        }
    }

    /**
     * 根据文件url获取文件名（包含后缀名）
     *
     * @param url 文件url
     * @return 文件名（包含后缀名）
     */
    private static String getOriginalFileName(String url)
    {
        String[] sarry = url.split("/");
        return sarry[sarry.length - 1];
    }

    private static List<Map<Integer, MusicLnfo>> fillData() {

        Map<Integer, MusicLnfo> map = new HashMap<>();
        List<Map<Integer, MusicLnfo>> list = new ArrayList<>();
        map.put(1, new MusicLnfo(1, prefixUrl + 151139, "风云 - (电视剧《风云2》片头曲)", "undefined"));
        map.put(2, new MusicLnfo(2, prefixUrl + 1457756795, "精忠报国 (Live)", "undefined"));
        map.put(3, new MusicLnfo(3, prefixUrl + 1459731368, "你 (Live) - (互动歌曲)", "undefined"));
        map.put(4, new MusicLnfo(4, prefixUrl + 1459731382, "江山无限 (Live) - (互动歌曲)", "undefined"));
        map.put(5, new MusicLnfo(5, prefixUrl + 32341872, "在劫难逃", "undefined"));
        map.put(6, new MusicLnfo(6, prefixUrl + 5283089, "太阳最红，毛主席最亲等联唱", "undefined"));
        map.put(7, new MusicLnfo(7, prefixUrl + 29131000, "独占潇洒", "undefined"));
        map.put(8, new MusicLnfo(8, prefixUrl + 486188239, "中国功夫", "undefined"));
        map.put(9, new MusicLnfo(9, prefixUrl + 27501799, "叶问霜天 - (电视剧《叶问》片头曲)", "undefined"));
        map.put(10, new MusicLnfo(10, prefixUrl + 27927037, "莽原 - (电视剧《太祖秘史》主题曲)", "undefined"));
        map.put(11, new MusicLnfo(11, prefixUrl + 5269896, "贵州山歌", "undefined"));
        map.put(12, new MusicLnfo(12, prefixUrl + 5283090, "共产党来了苦变甜等联唱", "undefined"));
        map.put(13, new MusicLnfo(13, prefixUrl + 27927039, "我的心 - (电视剧《武当》片头曲)", "undefined"));
        map.put(14, new MusicLnfo(14, prefixUrl + 5283092, "唱支山歌给党听等联唱", "undefined"));
        map.put(15, new MusicLnfo(15, prefixUrl + 536622055, "相信 - (电视剧《和平饭店》主题曲)", "undefined"));
        map.put(16, new MusicLnfo(16, prefixUrl + 5283091, "撒尼人民心向红太阳等联唱", "undefined"));
        map.put(17, new MusicLnfo(17, prefixUrl + 526468560, "龙行天下", "undefined"));
        map.put(18, new MusicLnfo(18, prefixUrl + 5283093, "海上女民兵等联唱", "undefined"));
        map.put(19, new MusicLnfo(19, prefixUrl + 26041331, "天上太阳红彤彤", "undefined"));
        map.put(20, new MusicLnfo(20, prefixUrl + 31563984, "乱世英雄 - (电视剧《吕不韦传奇》主题曲)", "undefined"));
        map.put(21, new MusicLnfo(21, prefixUrl + 5269942, "阿里山的姑娘", "undefined"));
        map.put(22, new MusicLnfo(22, prefixUrl + 5269901, "大板城的姑娘", "undefined"));
        map.put(23, new MusicLnfo(23, prefixUrl + 151502, "敢爱敢做", "undefined"));
        map.put(24, new MusicLnfo(24, prefixUrl + 181981, "忘情正好在今天", "undefined"));
        map.put(25, new MusicLnfo(25, prefixUrl + 151506, "中国龙", "undefined"));
        map.put(26, new MusicLnfo(26, prefixUrl + 151383, "战士歌唱东方红", "undefined"));
        map.put(27, new MusicLnfo(27, prefixUrl + 151569, "爱你在心口难开", "undefined"));
        map.put(28, new MusicLnfo(28, prefixUrl + 5269891, "小放牛", "undefined"));
        map.put(29, new MusicLnfo(29, prefixUrl + 5270068, "云南出来小马街", "undefined"));
        map.put(30, new MusicLnfo(30, prefixUrl + 5269889, "放马山歌", "undefined"));
        map.put(31, new MusicLnfo(31, prefixUrl + 409931075, "宴太平 - (电视剧《传奇大掌柜》片尾曲)", "undefined"));
        map.put(32, new MusicLnfo(32, prefixUrl + 5270050, "想亲亲", "undefined"));
        map.put(33, new MusicLnfo(33, prefixUrl + 27907962, "唯有兄弟", "undefined"));
        map.put(34, new MusicLnfo(34, prefixUrl + 29131002, "刀剑未必无情", "undefined"));
        map.put(35, new MusicLnfo(35, prefixUrl + 5269885, "斑鸠调", "undefined"));
        map.put(36, new MusicLnfo(36, prefixUrl + 5269915, "小黄鹂鸟", "undefined"));
        map.put(37, new MusicLnfo(37, prefixUrl + 1459730862, "我只在乎你 (Live) - (互动歌曲)", "undefined"));
        map.put(38, new MusicLnfo(38, prefixUrl + 5270065, "摘石榴", "undefined"));
        map.put(39, new MusicLnfo(39, prefixUrl + 27927040, "寻求 - (电视剧《封神榜》歌曲)", "undefined"));
        map.put(40, new MusicLnfo(40, prefixUrl + 5269905, "忠实的心哪想念你", "undefined"));
        map.put(41, new MusicLnfo(41, prefixUrl + 5270064, "赶马调", "undefined"));
        map.put(42, new MusicLnfo(42, prefixUrl + 5269946, "丢丢铜", "undefined"));
        map.put(43, new MusicLnfo(43, prefixUrl + 5269903, "打连城", "undefined"));
        map.put(44, new MusicLnfo(44, prefixUrl + 151454, "等待", "undefined"));
        map.put(45, new MusicLnfo(45, prefixUrl + 151511, "半个月亮", "undefined"));
        map.put(46, new MusicLnfo(46, prefixUrl + 5282619, "黄土地", "undefined"));
        map.put(47, new MusicLnfo(47, prefixUrl + 5270076, "螃蟹歌", "undefined"));
        map.put(48, new MusicLnfo(48, prefixUrl + 151548, "黑色的眼眸", "undefined"));
        map.put(49, new MusicLnfo(49, prefixUrl + 151556, "在无人的海边", "undefined"));


        list.add(map);
        return list;
    }

}
