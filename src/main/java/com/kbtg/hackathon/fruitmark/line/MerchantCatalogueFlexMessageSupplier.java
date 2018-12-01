package com.kbtg.hackathon.fruitmark.line;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
	
	@Override
	public FlexMessage get() {
		final Bubble bubble1 = createBubble("ร้าน 1", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble2 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble3 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble4 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble5 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
		final Bubble bubble6 = createBubble("ร้าน 2", "https://cloud-cube.s3.amazonaws.com/a57pmdvurwdr/public/dog.jpg");
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