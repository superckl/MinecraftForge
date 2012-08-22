package cpw.mods.mockmod;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Block;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.ModMetadata;

@Mod(modid="MockMod", name="Mock Mod",version="1.2.3", dependencies="before:mod_testMod", useMetadata=true)
@NetworkMod(channels={"MockMod"},clientSideRequired=true,packetHandler=MockMod.PacketHandler.class)
public class MockMod
{
    public static class PacketHandler implements IPacketHandler
    {
        @Override
        public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player)
        {
        }
    }
    public class TestItem extends ItemBlock
    {

        public TestItem(int id)
        {
            super(id);
        }

    }

    @Instance
    public static MockMod myInstance;

    @Metadata
    private ModMetadata meta;

    @Block(name="MyBlock", itemTypeClass=TestItem.class)
    private MockBlock myBlock;

    @PreInit
    public void preinit(FMLPreInitializationEvent evt)
    {
        System.out.printf("Hello from mockmod preinit %s\n", evt.getSourceFile());
        evt.getModMetadata().description="Fishy fish head";
        evt.getModMetadata().autogenerated = false;
    }
    @Init
    public void init(FMLInitializationEvent evt)
    {
        System.out.printf("Hello from mockmod init : %s %s\n", myInstance, meta);
    }
}