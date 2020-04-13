package com.gomai.feedback.vo;
import com.gomai.feedback.pojo.Report;
import com.gomai.feedback.pojo.ReportMedia;

import java.util.List;

public class R_RMdia {
    private Report report;
    private List<ReportMedia> reportMedia;

    public R_RMdia(Report report, List<ReportMedia> reportMedia) {
        this.report = report;
        this.reportMedia = reportMedia;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<ReportMedia> getReportMedia() {
        return reportMedia;
    }

    public void setReportMedia(List<ReportMedia> reportMedia) {
        this.reportMedia = reportMedia;
    }
}
