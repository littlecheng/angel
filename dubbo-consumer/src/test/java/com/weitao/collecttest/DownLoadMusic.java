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
        map.put(1, new MusicLnfo(1, prefixUrl + 22845457, "불꽃 - (火花)", "高耀太"));
        map.put(2, new MusicLnfo(2, prefixUrl + 115569, "护花使者", "李克勤"));
        map.put(3, new MusicLnfo(3, prefixUrl + 29809303, "打斗2", "武聆音雄"));
        map.put(4, new MusicLnfo(4, prefixUrl + 29999268, "余生恨更多", "黄邦贤"));
        map.put(5, new MusicLnfo(5, prefixUrl + 276492, "把他换作你", "孟庭苇"));
        map.put(6, new MusicLnfo(6, prefixUrl + 86444, "小李飞刀", "顾嘉辉"));
        map.put(7, new MusicLnfo(7, prefixUrl + 187797, "风雨无阻", "周华健"));
        map.put(8, new MusicLnfo(8, prefixUrl + 2117058, "Back To Black", "Amy Winehouse"));
        map.put(9, new MusicLnfo(9, prefixUrl + 276676, "你看你看月亮的脸", "孟庭苇"));
        map.put(10, new MusicLnfo(10, prefixUrl + 188820, "你好毒", "张学友"));
        map.put(11, new MusicLnfo(11, prefixUrl + 189987, "只想一生跟你走", "张学友"));
        map.put(12, new MusicLnfo(12, prefixUrl + 299116, "约定", "王菲"));
        map.put(13, new MusicLnfo(13, prefixUrl + 78168, "花好月圆", "刁寒"));
        map.put(14, new MusicLnfo(14, prefixUrl + 298880, "容易受伤的女人", "王菲"));
        map.put(15, new MusicLnfo(15, prefixUrl + 255950, "问", "林忆莲"));
        map.put(16, new MusicLnfo(16, prefixUrl + 190062, "记事本", "周传雄"));
        map.put(17, new MusicLnfo(17, prefixUrl + 144269, "我是一只小小鸟", "任贤齐/李宗盛"));
        map.put(18, new MusicLnfo(18, prefixUrl + 144638, "依靠", "任贤齐"));
        map.put(19, new MusicLnfo(19, prefixUrl + 28186082, "顺流逆流", "徐小凤"));
        map.put(20, new MusicLnfo(20, prefixUrl + 144515, "我是一只鱼", "任贤齐"));
        map.put(21, new MusicLnfo(21, prefixUrl + 5265772, "花好月圆夜 - (电影《花好月圆》插曲)", "任贤齐/杨千嬅"));
        map.put(22, new MusicLnfo(22, prefixUrl + 120255, "彩虹天堂", "刘畊宏"));
        map.put(23, new MusicLnfo(23, prefixUrl + 2530133, "One By One", "Enya"));
        map.put(24, new MusicLnfo(24, prefixUrl + 5145740, "Because I Love You", "Shakin' Stevens"));
        map.put(25, new MusicLnfo(25, prefixUrl + 96087, "爱情诺曼底", "黄征"));
        map.put(26, new MusicLnfo(26, prefixUrl + 5272610, "相约一九九八", "那英/王菲"));
        map.put(27, new MusicLnfo(27, prefixUrl + 157288, "挪威的森林", "伍佰 & China Blue"));
        map.put(28, new MusicLnfo(28, prefixUrl + 254265, "宁夏", "梁静茹"));
        map.put(29, new MusicLnfo(29, prefixUrl + 238658, "High歌", "黄龄"));
        map.put(30, new MusicLnfo(30, prefixUrl + 290544, "幸福快车", "孙悦"));
        map.put(31, new MusicLnfo(31, prefixUrl + 176339, "白色恋人", "游鸿明"));
        map.put(32, new MusicLnfo(32, prefixUrl + 176496, "爱我的人和我爱的人", "游鸿明"));
        map.put(33, new MusicLnfo(33, prefixUrl + 189873, "我等到花儿也谢了", "张学友"));
        map.put(34, new MusicLnfo(34, prefixUrl + 5057950, "Can You Feel The Love Tonight - (动画电影《狮子王》插曲/主题曲)", "Elton John"));
        map.put(35, new MusicLnfo(35, prefixUrl + 254424, "我喜欢", "梁静茹"));
        map.put(36, new MusicLnfo(36, prefixUrl + 2534006, "Big Big World", "Emilia"));
        map.put(37, new MusicLnfo(37, prefixUrl + 16232697, "Because of You", "Kelly Clarkson"));
        map.put(38, new MusicLnfo(38, prefixUrl + 27483167, "唯一", "王力宏"));
        map.put(39, new MusicLnfo(39, prefixUrl + 400161640, "算你狠", "陈小春"));
        map.put(40, new MusicLnfo(40, prefixUrl + 150996, "明天你是否依然爱我", "童安格"));
        map.put(41, new MusicLnfo(41, prefixUrl + 386538, "温柔", "五月天"));
        map.put(42, new MusicLnfo(42, prefixUrl + 5257056, "梁山伯与茱丽叶", "曹格/卓文萱"));
        map.put(43, new MusicLnfo(43, prefixUrl + 153784, "一生中最爱", "谭咏麟"));
        map.put(44, new MusicLnfo(44, prefixUrl + 92335, "射雕英雄传 - (电影《射雕英雄传之东成西就》片尾曲)", "林穆"));
        map.put(45, new MusicLnfo(45, prefixUrl + 186452, "最近比较烦", "周华健/品冠/李宗盛"));
        map.put(46, new MusicLnfo(46, prefixUrl + 139808, "快乐崇拜", "潘玮柏/张韶涵"));
        map.put(47, new MusicLnfo(47, prefixUrl + 354455, "流星雨", "F4"));
        map.put(48, new MusicLnfo(48, prefixUrl + 263691, "隔世感觉", "刘小慧"));
        map.put(49, new MusicLnfo(49, prefixUrl + 299075, "我愿意", "王菲"));
        map.put(50, new MusicLnfo(50, prefixUrl + 5245488, "专属天使 - (电视剧《花样少年少女》片尾曲)", "Tank"));
        map.put(51, new MusicLnfo(51, prefixUrl + 357263, "Don't Break My Heart", "黑豹乐队"));
        map.put(52, new MusicLnfo(52, prefixUrl + 188703, "李香兰", "张学友"));
        map.put(53, new MusicLnfo(53, prefixUrl + 346075, "真的爱你", "Beyond"));
        map.put(54, new MusicLnfo(54, prefixUrl + 68350, "梦中人 - (Dream Person)", "王菲"));
        map.put(55, new MusicLnfo(55, prefixUrl + 21234259, "Hero", "Mariah Carey"));
        map.put(56, new MusicLnfo(56, prefixUrl + 144254, "再出发", "任贤齐/阿牛"));
        map.put(57, new MusicLnfo(57, prefixUrl + 189315, "慢慢", "张学友"));
        map.put(58, new MusicLnfo(58, prefixUrl + 32192222, "知心爱人", "付笛声/任静"));
        map.put(59, new MusicLnfo(59, prefixUrl + 387671, "红蜻蜓+蝴蝶飞呀+叫你一声My Love(Live)", "小虎队"));
        map.put(60, new MusicLnfo(60, prefixUrl + 19047042, "Somewhere Only We Know", "Keane"));
        map.put(61, new MusicLnfo(61, prefixUrl + 196816, "不要再来伤害我", "张振宇"));
        map.put(62, new MusicLnfo(62, prefixUrl + 95843, "栀子花开", "何炅"));
        map.put(63, new MusicLnfo(63, prefixUrl + 144156, "对面的女孩看过来", "任贤齐"));
        map.put(64, new MusicLnfo(64, prefixUrl + 114442, "十七岁的雨季", "林志颖"));
        map.put(65, new MusicLnfo(65, prefixUrl + 4875037, "星光 - (电视剧《真命天女》片头曲)", "S.H.E"));
        map.put(66, new MusicLnfo(66, prefixUrl + 122926, "星光灿烂", "罗中旭"));
        map.put(67, new MusicLnfo(67, prefixUrl + 29130999, "神的传说", "毛阿敏"));
        map.put(68, new MusicLnfo(68, prefixUrl + 276362, "渴望 - (电视剧《渴望》主题曲)", "毛阿敏"));
        map.put(69, new MusicLnfo(69, prefixUrl + 32477996, "歌剧2", "洛天依"));
        map.put(70, new MusicLnfo(70, prefixUrl + 177346, "热情的沙漠", "庾澄庆"));
        map.put(71, new MusicLnfo(71, prefixUrl + 119659, "兰花草", "刘文正"));
        map.put(72, new MusicLnfo(72, prefixUrl + 811820, "白いスーツのテーマ", "市川淳"));
        map.put(73, new MusicLnfo(73, prefixUrl + 29717271, "Go Time", "Mark Petrie"));
        map.put(74, new MusicLnfo(74, prefixUrl + 4875084, "恶作剧", "王蓝茵"));
        map.put(75, new MusicLnfo(75, prefixUrl + 255588, "柿子", "林忆莲"));
        map.put(76, new MusicLnfo(76, prefixUrl + 32477997, "海豚音", "洛天依"));
        map.put(77, new MusicLnfo(77, prefixUrl + 862503252, "83射雕 铁血丹心 激昂版", "之乐而乐"));
        map.put(78, new MusicLnfo(78, prefixUrl + 387826, "星光依旧灿烂", "小虎队"));
        map.put(79, new MusicLnfo(79, prefixUrl + 28427754, "天边 (Live) - (原唱：布仁巴雅尔)", "韩磊"));
        map.put(80, new MusicLnfo(80, prefixUrl + 300123, "流星", "王菲"));
        map.put(81, new MusicLnfo(81, prefixUrl + 5269070, "干杯朋友", "田震"));
        map.put(82, new MusicLnfo(82, prefixUrl + 326919, "原来你什么都不要", "张惠妹"));
        map.put(83, new MusicLnfo(83, prefixUrl + 213890, "信天游", "程琳"));
        map.put(84, new MusicLnfo(84, prefixUrl + 354620, "只对你有感觉", "飞轮海/田馥甄"));
        map.put(85, new MusicLnfo(85, prefixUrl + 5270839, "小草", "殷秀梅"));
        map.put(86, new MusicLnfo(86, prefixUrl + 237205, "采槟榔", "高胜美"));
        map.put(87, new MusicLnfo(87, prefixUrl + 65501, "同一首歌", "蔡国庆"));
        map.put(88, new MusicLnfo(88, prefixUrl + 5274936, "十五的月亮十六圆", "范琳琳"));
        map.put(89, new MusicLnfo(89, prefixUrl + 139764, "不得不爱", "潘玮柏/弦子"));
        map.put(90, new MusicLnfo(90, prefixUrl + 229729, "你怎么说", "邓丽君"));
        map.put(91, new MusicLnfo(91, prefixUrl + 156968, "最爱是你", "伍思凯"));
        map.put(92, new MusicLnfo(92, prefixUrl + 109505, "青春舞曲", "罗大佑"));
        map.put(93, new MusicLnfo(93, prefixUrl + 214270, "庭院深深", "蔡幸娟"));
        map.put(94, new MusicLnfo(94, prefixUrl + 19153941, "Whenever, Wherever", "Shakira"));
        map.put(95, new MusicLnfo(95, prefixUrl + 5280125, "春天的花夏天的雨", "黄鹤翔"));
        map.put(96, new MusicLnfo(96, prefixUrl + 518895387, "女孩的心思你别猜 (演唱会版)", "周亮"));
        map.put(97, new MusicLnfo(97, prefixUrl + 402179145, "Sha La La La", "温拿"));
        map.put(98, new MusicLnfo(98, prefixUrl + 1498796, "Rhythm Of The Rain", "Jason Donovan"));
        map.put(99, new MusicLnfo(99, prefixUrl + 254552, "我曾爱过一个男孩", "刘若英"));
        map.put(100, new MusicLnfo(100, prefixUrl + 187981, "明天我要嫁给你", "周华健"));
        map.put(101, new MusicLnfo(101, prefixUrl + 235985, "春风吻上我的脸", "高胜美"));
        map.put(102, new MusicLnfo(102, prefixUrl + 5280147, "今宵情 - (中央电视台综艺大观主题曲)", "毛阿敏"));
        map.put(103, new MusicLnfo(103, prefixUrl + 82284, "秋意浓", "范宗沛"));
        map.put(104, new MusicLnfo(104, prefixUrl + 104241, "生日礼物", "江涛"));
        map.put(105, new MusicLnfo(105, prefixUrl + 30031088, "少林雄风", "黄霑/鲍比达"));
        map.put(106, new MusicLnfo(106, prefixUrl + 29984832, "离手剑", "黄邦贤"));
        map.put(107, new MusicLnfo(107, prefixUrl + 29809301, "余生恨更多慢版", "武聆音雄"));
        map.put(108, new MusicLnfo(108, prefixUrl + 5256050, "摆脱身体", "蒋丽萍"));
        map.put(109, new MusicLnfo(109, prefixUrl + 18611643, "I'm Yours", "Jason Mraz"));
        map.put(110, new MusicLnfo(110, prefixUrl + 25642367, "雨夜钢琴", "林志美"));
        map.put(111, new MusicLnfo(111, prefixUrl + 260417, "劲之夜", "林志美"));
        map.put(112, new MusicLnfo(112, prefixUrl + 307373, "美梦成真 - (电影《美梦成真》中文主题曲)", "许茹芸"));
        map.put(113, new MusicLnfo(113, prefixUrl + 190880, "Cry!", "张学友"));
        map.put(114, new MusicLnfo(114, prefixUrl + 27566922, "Roar", "Katy Perry"));
        map.put(115, new MusicLnfo(115, prefixUrl + 188884, "Ooh La La(Live)", "张学友"));
        map.put(116, new MusicLnfo(116, prefixUrl + 94584, "头发乱了", "张学友"));
        map.put(117, new MusicLnfo(117, prefixUrl + 190701, "情不禁", "张学友"));
        map.put(118, new MusicLnfo(118, prefixUrl + 168581, "爱情电影 - (电视剧《新江山美人》主题曲)", "熊天平/许茹芸"));
        map.put(119, new MusicLnfo(119, prefixUrl + 63614, "从头再来", "崔健"));
        map.put(120, new MusicLnfo(120, prefixUrl + 26880581, "Dreams", "The Cranberries"));
        map.put(121, new MusicLnfo(121, prefixUrl + 30031072, "阿朱之死", "陈国樑"));
        map.put(122, new MusicLnfo(122, prefixUrl + 30031892, "浩然之气(空见出场曲)", "武聆音雄"));
        map.put(123, new MusicLnfo(123, prefixUrl + 30031162, "论形势", "武聆音雄"));
        map.put(124, new MusicLnfo(124, prefixUrl + 121234, "爱我就别伤害我", "刘嘉亮"));
        map.put(125, new MusicLnfo(125, prefixUrl + 30031167, "城堡 - (原曲：Giacomo Bondi - One Minute After（Edit）)", "武聆音雄"));
        map.put(126, new MusicLnfo(126, prefixUrl + 29984824, "屠狮大会", "陈国梁"));
        map.put(127, new MusicLnfo(127, prefixUrl + 30031027, "梦郎梦姑重逢", "陈国樑"));
        map.put(128, new MusicLnfo(128, prefixUrl + 29999307, "雁门关(悲情身世)", "陈国樑"));
        map.put(129, new MusicLnfo(129, prefixUrl + 253145, "她在睡前哭泣", "柯以敏/李玟"));
        map.put(130, new MusicLnfo(130, prefixUrl + 764560, "魂斗羅（FC）密林の戦い~戦慄の鼓動~凱旋1", "前沢秀憲/禎清宏"));
        map.put(131, new MusicLnfo(131, prefixUrl + 122021, "一封家书", "李春波"));
        map.put(132, new MusicLnfo(132, prefixUrl + 255501, "真想见到你", "李玟"));
        map.put(133, new MusicLnfo(133, prefixUrl + 95428, "陪你到天涯 - (电视剧黄土地外的天空》主题曲)", "黄安"));
        map.put(134, new MusicLnfo(134, prefixUrl + 30798221, "Intimidation", "Mark Petrie"));
        map.put(135, new MusicLnfo(135, prefixUrl + 234687, "一切也愿意 - (电视剧《蓝色风暴》主题曲)", "关淑怡"));
        map.put(136, new MusicLnfo(136, prefixUrl + 121266, "我说我爱你", "刘嘉亮"));
        map.put(137, new MusicLnfo(137, prefixUrl + 4874741, "放不下 - (电视剧《东方茱丽叶》配乐)", "龚诗嘉"));
        map.put(138, new MusicLnfo(138, prefixUrl + 5251209, "承诺(香港版)", "郑融/林子祥/梁汉文/莫文蔚/梁咏琪/钟镇涛/郑欣宜/侧田/汪明荃/at17/方大同/许志安/谭咏麟/苏永康/曾志伟/容祖儿/泰迪罗宾/杨千嬅/王菀之/陈奕迅/肥妈/刘德华/张学友/林依轮/黄家强/李克勤/罗家英"));
        map.put(139, new MusicLnfo(139, prefixUrl + 261541, "昨夜星辰", "龙飘飘"));
        map.put(140, new MusicLnfo(140, prefixUrl + 93661, "花开在眼前 - (央视纪录片《激荡·1978-2008》片尾曲)", "韩磊"));
        map.put(141, new MusicLnfo(141, prefixUrl + 173819, "笑脸", "谢东"));
        map.put(142, new MusicLnfo(142, prefixUrl + 5276907, "中国娃", "解晓东"));
        map.put(143, new MusicLnfo(143, prefixUrl + 5261272, "刺激2005", "赵英俊"));
        map.put(144, new MusicLnfo(144, prefixUrl + 176430, "爱上你是一个错", "杨培安"));
        map.put(145, new MusicLnfo(145, prefixUrl + 109729, "离人", "林志炫"));
        map.put(146, new MusicLnfo(146, prefixUrl + 186420, "别傻了", "周华健/任贤齐"));
        map.put(147, new MusicLnfo(147, prefixUrl + 276330, "永远是朋友", "毛阿敏"));

        list.add(map);
        return list;
    }

}
