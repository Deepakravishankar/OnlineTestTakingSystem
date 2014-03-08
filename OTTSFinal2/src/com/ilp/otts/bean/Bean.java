package com.ilp.otts.bean;

public class Bean {
	
	
	   
		 String result;
		 String percentage;
		public String testid;	
		public String studentid;
		public String resultid;
		public String score;
		public String avg,schoolid;
		boolean valid;
		String awardid;
		String awardtype;
		String awardname;
		String awardamount;
		String awardreason;
		String awarddetails;
		String low,high;

		
		
		public String getPercentage() {
			return percentage;
		}
		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}
		
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
					
		public String getAwardid() {
		return awardid;
					}

		public void setAwardid(String awardid) {
		this.awardid = awardid;
					}

		public String getAwardtype() {
		return awardtype;
					}

		public void setAwardtype(String awardtype) {
			this.awardtype = awardtype;
					}
					public String getAwardname() {
						return awardname;
					}

					public void setAwardname(String awardname) {
						this.awardname = awardname;
					}

					public String getAwardamount() {
						return awardamount;
			}

					public void setAwardamount(String awardamount) {
						this.awardamount = awardamount;
			}
					public String getAwardreason() {
						return awardreason;
			}

					public void setAwardreason(String awardreason) {
						this.awardreason = awardreason;
			}
					public String getAwarddetails() {
						return awarddetails;
					}

					public void setAwarddetails(String awarddetails) {
						this.awarddetails = awarddetails;
			}
					public String display(){
						return 	awardid+" "+awardtype+" "+awardname+" "+awardamount+" "+awardreason+" "+awarddetails;
					}
					
					public String getLow() {
						return low;
					}

					public void setLow(String low) {
						this.low = low;
					}
					
					public String getHigh() {
						return high;
					}

					public void setHigh(String high) {
						this.high = high;
					}

					public String getSchoolid() {
						return schoolid;
					}

					public void setSchoolid(String avg) {
						this.schoolid = avg;
					}


					public String getAverage() {
						return avg;
					}

					public void setAverage(String avg) {
						this.avg = avg;
					}


							public String getStudentid() {
								return studentid;
							}

							public void setStudentid(String studentid) {
								this.studentid = studentid;
							}

							

							public void setResultid(String resultid) {
								this.resultid = resultid;
							}
							public String getResultid(){
								return resultid;
							}
							public String getScore() {
								return score;
							}

							public void setScore(String score) {
								this.score = score;
							}

							

							public String getTestid() {
								return testid;
							}

							public void setTestid(String testid) {
								this.testid = testid;
							}

	
								}
