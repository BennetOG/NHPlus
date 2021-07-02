package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Treatment {
    private long tid;
    private long pid;
    private LocalDate date;
    private LocalDateTime begin;
    private LocalDateTime end;
    private String description;
    private String remarks;

    public Treatment(long pid, LocalDate date, LocalDateTime begin,
                     LocalDateTime end, String description, String remarks) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    public Treatment(long tid, long pid, LocalDate date, LocalDateTime begin,
                     LocalDateTime end, String description, String remarks) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    public long getTid() {
        return tid;
    }

    public long getPid() {
        return this.pid;
    }

    public String getDate() {
        return date.toString();
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public void setBegin(String begin) {
        this.begin = DateConverter.convertStringToLocalDateTime(begin);
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setEnd(String end) {
        this.end = DateConverter.convertStringToLocalDateTime(end);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}