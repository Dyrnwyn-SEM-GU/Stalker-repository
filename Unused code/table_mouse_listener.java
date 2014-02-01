	
		/* 
		 *  Written by DANIELLE 
		 * 
		 * mouse listener for the table, 
		 * used to show picture resized in a frame, 
		 * when the file cell in jtable is clicked. 
		 * Error has been solved.
		 * TODO create extra costs submission UI
		 *  and actionlistener and connect to this method
		 * 
		 */
		reportTable.addMouseListener(new MouseAdapter()  { 

			public void mouseClicked(MouseEvent e)  {  


				if(e.getClickCount() == 1) {  

					int row = reportTable.getSelectedRow(); 
					int column = reportTable.getSelectedColumn();   
					String colName = reportTable.getColumnName(column); 


					if (colName.equalsIgnoreCase("File")) {  

						String value =  (String) reportTable.getValueAt(row, 5); 


						try {

							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
									"username", "password");  
							Statement st=con.createStatement();   

							ResultSet rs = st.executeQuery( "select  file from extracosts where IDCosts =  " + value) ;

							while(rs.next())
							{


								byte[] imageByte;
								imageByte = rs.getBytes("file");
								InputStream in = new ByteArrayInputStream(imageByte); 
								final BufferedImage bufferedImage = ImageIO.read(in); 


								JLabel label = new JLabel() { 

									protected void paintComponent(Graphics g){
										Graphics graph = g.create();
										graph.drawImage(bufferedImage, 0, 0, 600, 300, null);  
										graph.dispose();
									}


									public Dimension getPreferredSize(){

										//return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()); 

										return new Dimension(600, 300);
									} 


								};


								JFrame frame = new JFrame(); 
								frame.setLocationRelativeTo(null);
								frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								frame.add(label);
								frame.pack();
								frame.setVisible(true);

							} 
						} 
						catch (ClassNotFoundException e1) {

							e1.printStackTrace();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} catch (IOException e1) {

							e1.printStackTrace();
						}




					}



				}

			}





		});