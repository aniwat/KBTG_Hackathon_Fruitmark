package com.kbtg.hackathon.fruitmark.line;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.kbtg.hackathon.fruitmark.entity.Merchant;
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

public class MerchantCatalogueFlexMessageSupplier implements Supplier<FlexMessage> {
	
	private List<Merchant> list;
	
	public List<Merchant> getList() {
		return list;
	}
	
	public void setList(List<Merchant> list) {
		this.list = list;
	}
	
	@Override
	public FlexMessage get() {
		ArrayList<Bubble> bList = new ArrayList<Bubble>();
		if (list != null && list.size() > 0) {
			int count = 0;
			for (Merchant m : list) {
				bList.add(createBubble(m.getMerchantName(), m.getMerchantImg()));
				if (++count == 10) {
					break;
				}
			}
		}
		final Carousel carousel = Carousel.builder().contents(bList).build();
		return new FlexMessage("เลือกร้านค้า", carousel);
	}
	
	public FlexMessage getTest() {
		final Bubble bubble1 = createBubble("ร้าน 1", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble2 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble3 = createBubble("ร้าน 3", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble4 = createBubble("ร้าน 4", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble5 = createBubble("ร้าน 5", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble6 = createBubble("ร้าน 6", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Carousel carousel = Carousel.builder().contents(asList(bubble1, bubble2, bubble3, bubble4, bubble5, bubble6)).build();
		return new FlexMessage("เลือกร้านค้า", carousel);
	}
	
	private Bubble createBubble(String title, String imageURL) {
		final Image heroBlock = createHeroBlock(imageURL);
		final Box bodyBlock = createBodyBlock(title);
		final Box footerBlock = createFooterBlock();
		return Bubble.builder().hero(heroBlock).body(bodyBlock).footer(footerBlock).build();
	}
	
	private Image createHeroBlock(String imageURL) {
		return Image.builder().size(Image.ImageSize.FULL_WIDTH).aspectRatio(Image.ImageAspectRatio.R20TO13).aspectMode(Image.ImageAspectMode.Cover).url(imageURL).build();
	}
	
	private Box createBodyBlock(String title) {
		final Text titleBlock = Text.builder().text(title).wrap(true).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).build();
		FlexComponent[] flexComponents = { titleBlock };
		List<FlexComponent> listComponent = new ArrayList<>(Arrays.asList(flexComponents));
		
		return Box.builder().layout(FlexLayout.VERTICAL).spacing(FlexMarginSize.SM).contents(listComponent).build();
	}
	
	private Box createFooterBlock() {
		final Button addToCartEnableButton = Button.builder().style(Button.ButtonStyle.PRIMARY).action(new URIAction("เยี่ยมชม", "http://example.com")).build();
		return Box.builder().layout(FlexLayout.VERTICAL).spacing(FlexMarginSize.SM).contents(asList(addToCartEnableButton)).build();
	}
	
}