package com.aidiapp.salonbike.kml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;



public class BikeLaneSaxHandler extends DefaultHandler {
	// =========================================================== 
		 // Campos 
		 // =========================================================== 

		 private boolean in_kmltag = false; 
		 private boolean in_placemarktag = false; 
		 private boolean in_nametag = false;
		 private boolean in_descriptiontag = false;
		 private boolean in_geometrycollectiontag = false;
		 private boolean in_linestringtag = false;
		 private boolean in_pointtag = false;
		 private boolean in_coordinatestag = false;

		 private StringBuilder buffer;
		 
		 private BikeLaneDataSet bikelaneDataset=new BikeLaneDataSet();
		 
		 public BikeLaneDataSet getBikelaneDataset() {
			return bikelaneDataset;
		}
		 
		 @Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			this.bikelaneDataset=new BikeLaneDataSet();
			
		}
		 
		 @Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			 Log.d("SAXHANDLER", "Queremos abrir la etiqueta "+localName);
		      if (localName.equals("kml")) { 
		           this.in_kmltag = true;
		      } else if (localName.equals("Placemark")) { 
		           this.in_placemarktag = true; 
		           this.bikelaneDataset.setCurrentBikeLane(new BikeLane());
		          // bikestationDataSet.setCurrentPlacemark(new BikeStation());
		           
		      } else if (localName.equals("name")) { 
		           this.in_nametag = true;
		      } else if (localName.equals("description")) { 
		          this.in_descriptiontag = true;
		      } else if (localName.equals("GeometryCollection")) { 
		          this.in_geometrycollectiontag = true;
		      } else if (localName.equals("LineString")) { 
		          this.in_linestringtag = true;              
		      } else if (localName.equals("Point")) { 
		          this.in_pointtag = true;          
		      } else if (localName.equals("coordinates")) {
		          buffer = new StringBuilder();
		          this.in_coordinatestag = true;                        
		      }
			super.startElement(uri, localName, qName, attributes);
		}
		 
		 @Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			 Log.d("SAXHANDLER", "Queremos cerrar la etiqueta "+localName);
		       if (localName.equals("kml")) {
		           this.in_kmltag = false; 
		       } else if (localName.equals("Placemark")) { 
		           this.in_placemarktag = false;

		        this.bikelaneDataset.addCurrentBikeLane();

		       } else if (localName.equals("name")) { 
		           this.in_nametag = false;           
		       } else if (localName.equals("description")) { 
		           this.in_descriptiontag = false;
		       } else if (localName.equals("GeometryCollection")) { 
		           this.in_geometrycollectiontag = false;
		       } else if (localName.equals("LineString")) { 
		           this.in_linestringtag = false;              
		       } else if (localName.equals("Point")) { 
		           this.in_pointtag = false;          
		       } else if (localName.equals("coordinates")) { 
		           this.in_coordinatestag = false;
		           if (this.bikelaneDataset.getCurrentBikeLane()==null) bikelaneDataset.setCurrentBikeLane(new BikeLane());
			        //navigationDataSet.getCurrentPlacemark().setCoordinates(new String(ch, start, length));
			        bikelaneDataset.getCurrentBikeLane().setCoordinates(buffer.toString().trim());
		       }
			super.endElement(uri, localName, qName);
		}
		 
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			
			 if(this.in_nametag){ 
			        if (this.bikelaneDataset.getCurrentBikeLane()==null) bikelaneDataset.setCurrentBikeLane(new BikeLane());
			        bikelaneDataset.getCurrentBikeLane().setTitle(new String(ch, start, length));
			                  
			    } else 
			    
			    if(this.in_coordinatestag){  
			    	
			        
			        buffer.append(new String(ch,start,length));
			    }
		} 
		 @Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
		}
}
