package dto;

public class Notice {
    private  int noticeNo;
    private  String noticeTitle;
    private  String noticeContent;
    private  String createDate;

    private int noticeRead;

    public int getNoticeRead() {
        return noticeRead;
    }

    public void setNoticeRead(int noticeRead) {
        this.noticeRead = noticeRead;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }



    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNo=" + noticeNo +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", createDate='" + createDate + '\'' +
                ", noticeRead=" + noticeRead +
                '}';
    }
}
