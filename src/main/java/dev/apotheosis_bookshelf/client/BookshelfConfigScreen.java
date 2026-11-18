package dev.apotheosis_bookshelf.client;

import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlockEntity;
import dev.apotheosis_bookshelf.menu.BookshelfConfigMenu;
import dev.apotheosis_bookshelf.network.ModNetwork;
import dev.apotheosis_bookshelf.network.SetBookshelfValuePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ForgeSlider;

/**
 * Configuration screen for setting a bookshelf's stat value from 1 to 50.
 */
public class BookshelfConfigScreen extends AbstractContainerScreen<BookshelfConfigMenu> {
    private int currentValue;
    private ForgeSlider slider;
    private EditBox inputBox;
    private boolean updatingFromWidget = false;

    public BookshelfConfigScreen(BookshelfConfigMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.currentValue = Mth.clamp(menu.getInitialValue(),
                ConfigurableBookshelfBlockEntity.MIN_VALUE,
                ConfigurableBookshelfBlockEntity.MAX_VALUE);
        this.imageWidth = 220;
        this.imageHeight = 132;
    }

    @Override
    protected void init() {
        super.init();
        int left = this.leftPos;
        int top = this.topPos;

        this.slider = new ForgeSlider(
                left + 16, top + 34, 188, 20,
                Component.translatable(menu.getStatType().getTranslationKey()).append(": "),
                Component.empty(),
                ConfigurableBookshelfBlockEntity.MIN_VALUE,
                ConfigurableBookshelfBlockEntity.MAX_VALUE,
                currentValue,
                1.0, 0, true) {
            @Override
            protected void applyValue() {
                if (!updatingFromWidget) {
                    setValueAndSync(this.getValueInt(), true);
                }
            }
        };
        addRenderableWidget(this.slider);

        // Quick adjustment buttons: -10, -1, [input], +1, +10
        addRenderableWidget(Button.builder(Component.literal("-10"), b -> adjustValue(-10))
                .bounds(left + 16, top + 62, 32, 20).build());
        addRenderableWidget(Button.builder(Component.literal("-1"), b -> adjustValue(-1))
                .bounds(left + 52, top + 62, 28, 20).build());

        this.inputBox = new EditBox(this.font, left + 86, top + 62, 48, 20, Component.empty());
        this.inputBox.setMaxLength(2);
        this.inputBox.setFilter(s -> s.isEmpty() || s.chars().allMatch(Character::isDigit));
        this.inputBox.setValue(String.valueOf(currentValue));
        this.inputBox.setResponder(text -> {
            if (updatingFromWidget || text.isEmpty()) {
                return;
            }
            try {
                int parsed = Integer.parseInt(text);
                int clamped = Mth.clamp(parsed,
                        ConfigurableBookshelfBlockEntity.MIN_VALUE,
                        ConfigurableBookshelfBlockEntity.MAX_VALUE);
                setValueAndSync(clamped, false);
            } catch (NumberFormatException ignored) {
            }
        });
        addRenderableWidget(this.inputBox);

        addRenderableWidget(Button.builder(Component.literal("+1"), b -> adjustValue(1))
                .bounds(left + 140, top + 62, 28, 20).build());
        addRenderableWidget(Button.builder(Component.literal("+10"), b -> adjustValue(10))
                .bounds(left + 172, top + 62, 32, 20).build());

        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> this.onClose())
                .bounds(left + 60, top + 96, 100, 20).build());
    }

    private void adjustValue(int delta) {
        setValueAndSync(currentValue + delta, true);
    }

    private void setValueAndSync(int newValue, boolean updateInputBox) {
        int clamped = Mth.clamp(newValue,
                ConfigurableBookshelfBlockEntity.MIN_VALUE,
                ConfigurableBookshelfBlockEntity.MAX_VALUE);
        this.currentValue = clamped;

        this.updatingFromWidget = true;
        if (this.slider != null && this.slider.getValueInt() != clamped) {
            this.slider.setValue(clamped);
        }
        if (updateInputBox && this.inputBox != null) {
            this.inputBox.setValue(String.valueOf(clamped));
        }
        this.updatingFromWidget = false;

        ModNetwork.CHANNEL.sendToServer(new SetBookshelfValuePacket(menu.getPos(), clamped));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;
        // Draw dark panel with subtle border
        graphics.fill(x, y, x + this.imageWidth, y + this.imageHeight, 0xE0181820);
        graphics.renderOutline(x, y, this.imageWidth, this.imageHeight, 0xFF5A5A72);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        int titleWidth = this.font.width(this.title);
        graphics.drawString(this.font, this.title, (this.imageWidth - titleWidth) / 2, 12, 0xFFFFFF, false);
    }
}
