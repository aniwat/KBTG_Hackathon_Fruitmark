package com.kbtg.hackathon.fruitmark.line;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.kbtg.hackathon.fruitmark.entity.Product;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class ProductCatalogueFlexMessageSupplier implements Supplier<FlexMessage> {
	
	private List<Product> list;
	
	public List<Product> getList() {
		return list;
	}
	
	public void setList(List<Product> list) {
		this.list = list;
	}
	
	@Override
	public FlexMessage get() {
		ArrayList<Bubble> bList = new ArrayList<Bubble>();
		if (list != null && list.size() > 0) {
			int count = 0;
			for (Product p : list) {
				System.out.println("p.getProductName() = " + p.getProductName());
				System.out.println("p.getPricePerUnit() = " + p.getPricePerUnit());
				System.out.println("p.getProductImg() = " + p.getProductImg());
				bList.add(createBubble(p.getProductName(), p.getPricePerUnit(), p.getProductImg()));
				if (++count == 10) {
					break;
				}
			}
		}
		final Carousel carousel = Carousel.builder().contents(bList).build();
		return new FlexMessage("เลือกซื้อสินค้า", carousel);
	}
	
	public FlexMessage getTest() {
		final Bubble bubble1 = createBubble("มะละกอ", "199.00", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble2 = createBubble("กล้วย", "19.00", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble3 = createBubble("ส้ม", "19.00", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble4 = createBubble("มังคุด", "19.00", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Carousel carousel = Carousel.builder().contents(asList(bubble1, bubble2, bubble3, bubble4)).build();
		return new FlexMessage("เลือกซื้อสินค้า", carousel);
	}
	
	private Bubble createBubble(String title, String price, String imageURL) {
		final Image heroBlock = createHeroBlock(imageURL);
		final Box bodyBlock = createBodyBlock(title, price);
		final Box footerBlock = createFooterBlock();
		return Bubble.builder().hero(heroBlock).body(bodyBlock).footer(footerBlock).build();
	}
	
	private Image createHeroBlock(String imageURL) {
		return Image.builder().size(Image.ImageSize.FULL_WIDTH).aspectRatio(Image.ImageAspectRatio.R20TO13).aspectMode(Image.ImageAspectMode.Cover).url(imageURL).build();
	}
	
	private Box createBodyBlock(String title, String price) {
		final Text titleBlock = Text.builder().text(title).wrap(true).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).build();
		final Box priceBlock = Box.builder().layout(FlexLayout.BASELINE)
		    .contents(asList(Text.builder().text("฿ " + price.split("\\.")[0]).wrap(true).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).flex(0).build(),
		        Text.builder().text("." + price.split("\\.")[1]).wrap(true).weight(Text.TextWeight.BOLD).size(FlexFontSize.SM).flex(0).build()))
		    .build();
		
		FlexComponent[] flexComponents = { titleBlock, priceBlock };
		List<FlexComponent> listComponent = new ArrayList<>(Arrays.asList(flexComponents));
		
		return Box.builder().layout(FlexLayout.VERTICAL).spacing(FlexMarginSize.SM).contents(listComponent).build();
	}
	
	private Box createFooterBlock() {
		final Button addToCartEnableButton = Button.builder().style(Button.ButtonStyle.PRIMARY).action(new URIAction("หยิบใส่ตะกร้า", "http://example.com")).build();
		return Box.builder().layout(FlexLayout.VERTICAL).spacing(FlexMarginSize.SM).contents(asList(addToCartEnableButton)).build();
	}
	
}